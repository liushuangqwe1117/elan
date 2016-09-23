package com.redrock.elan.mgm.system.shiro;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.ylinkpay.framework.web.base.common.SessionKey;

import com.redrock.elan.common.core.constant.EUserStatus;
import com.redrock.elan.common.project.mgm.dto.security.SecUserDto;
import com.redrock.elan.mgm.security.service.SecUserService;
import com.redrock.elan.mgm.system.shiro.exception.FoundUserErrorException;
import com.redrock.elan.mgm.system.shiro.exception.ShiroBadCaptchaException;
import com.redrock.elan.mgm.system.shiro.exception.TimeLockedException;
import com.redrock.elan.mgm.system.shiro.exception.UserNotFoundException;
import com.redrock.elan.mgm.util.PropertyUtil;

public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
	
	private static Logger log = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);
	
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	protected String captchaParam = DEFAULT_CAPTCHA_PARAM;
	
	@Autowired
	protected SecUserService secUserService;

	public String getCaptchaParam() {
		return this.captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	protected CaptchaUsernamePasswordToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		return new CaptchaUsernamePasswordToken(username, password, rememberMe,host, captcha);
	}

	protected void doCaptchaValidate(HttpServletRequest request,CaptchaUsernamePasswordToken token) {
		if (isCheckCaptcha(request)) {
			String captcha = (String) request.getSession().getAttribute(getKaptchaSessionKey());

			if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha()))
				throw new ShiroBadCaptchaException("验证码错误！");
		}
	}

	protected boolean isCheckCaptcha(HttpServletRequest request) {
		return true;
	}
	
	protected String getKaptchaSessionKey() {
		return com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
	}

	protected void doBusinessValidate(SecUserDto user) 
			throws UserNotFoundException, LockedAccountException,TimeLockedException {
		if (user == null) {
			throw new UserNotFoundException("用户名或密码错误");
		}
		if (EUserStatus.WAIT_ACTIVE.equals(user.getStatus())) {
			throw new LockedAccountException("该账号未激活！");
		}
		if (EUserStatus.DISABLED.equals(user.getStatus()) ||
				EUserStatus.DELETE.equals(user.getStatus())) {
			throw new LockedAccountException("该账号已停用或注销！");
		}
		int time = 120;//分钟
		try {
			time = Integer.parseInt(PropertyUtil.getProperty(ShiroConstants.LOGIN_FAILURE_LOCK_MINUTES, String.valueOf(time)));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
		Date lockedTime = user.getLastLoginLockTime();
		if(lockedTime != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(lockedTime);
			c.add(Calendar.MINUTE, time);
			
			long curDate = new Date().getTime();
			long lockDate = c.getTimeInMillis();
			if(lockDate > curDate) {
				long minute = (lockDate - curDate)/(1000 * 60) + 1;
				throw new TimeLockedException("用户账号已被锁定," + minute + "分钟后重试");
			}
		}
	}

	/**
	 * 获取用户
	 * 
	 * @param username
	 * @return
	 * @throws CbsCheckedException 
	 */
	protected SecUserDto fetchUser(String username) throws FoundUserErrorException {
		if (username == null) {
			throw new UserNotFoundException("用户名不能为空");
		}
		try {
			return secUserService.getByLoginName(username);
		} catch (Exception e) {
			log.error("获取用户信息失败：" + e.getMessage());
			throw new FoundUserErrorException("获取用户信息失败");
		}
	}

	protected void doLock(SecUserDto user, ServletRequest request) {
		if(user == null)return ;
		
		int times = 6;
		try {
			times = Integer.parseInt(PropertyUtil.getProperty(ShiroConstants.LOGIN_FAILURE_LOCK_TIMES, String.valueOf(times)));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
		long failTimes = (user.getLoginErrorTimes() == null ? 0 : user.getLoginErrorTimes()) + 1;
		
		if (failTimes >= times) {
			try {
				secUserService.lockUser(user.getId());
			} catch (Exception e) {
				log.warn("锁定用户失败：", e);
			}
		} else {
			//更新错误次数
			SecUserDto entity = new SecUserDto();
			entity.setId(user.getId());
			entity.setLoginErrorTimes(failTimes);
			try {
				secUserService.updateSelective(entity);
			} catch (Exception e) {
				log.warn("更新用户登录失败次数：", e);
			}
		}
	}

	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = createToken(request, response);
		if (token == null) {
			String msg = "方法createToken()返回空值，登录操作必须获得非空的AuthenticationToken！";
			throw new IllegalStateException(msg);
		}

		SecUserDto user = null;
		try {
			user = fetchUser(token.getUsername());
			
			//校验验证码
			doCaptchaValidate((HttpServletRequest) request, token);
			
			doBusinessValidate(user);

			Subject subject = getSubject(request, response);
			subject.login(token);

			//登录成功
			writeUserToSession(user, (HttpServletRequest) request);
			secUserService.loginSuccess(user.getId());
			
			return onLoginSuccess(token, subject, request, response);
		} catch (UserNotFoundException | LockedAccountException | TimeLockedException | FoundUserErrorException e) {
			return onLoginFailure(token, e, request, response);
		}  catch (AuthenticationException e) {
			if(user != null) {
				doLock(user , request);
			}
			return onLoginFailure(token, e, request, response);
		} catch(Exception e){
			return onLoginFailure(token, new AuthenticationException("登录异常",e), request, response);
		}
	}
	
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		log.error(e.getMessage(),e);
		
		return super.onLoginFailure(token, e, request, response);
	}

	/**
	 * 将用户写入Session
	 * @param account
	 * @param request
	 * @author LS
	 * @date 2013-8-1
	 */
	protected void writeUserToSession(SecUserDto account, HttpServletRequest request) {
		request.getSession().setAttribute(SessionKey.LOGIN_ACCOUNT, account);
	}
	
	protected SecUserDto getSessionUser(HttpServletRequest request) {
		return (SecUserDto)request.getSession().getAttribute(SessionKey.LOGIN_ACCOUNT);
	}

	@Override
	protected void saveRequestAndRedirectToLogin(ServletRequest request,
			ServletResponse response) throws IOException {
		redirectToLogin(request, response);
	}

	protected void setFailureAttribute(ServletRequest request,
			AuthenticationException ae) {
		request.setAttribute(getFailureKeyAttribute(), ae);
	}
}
