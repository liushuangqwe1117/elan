package com.redrock.elan.mgm.system.context;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.ylinkpay.framework.core.util.RequestUtil;
import org.ylinkpay.framework.web.base.common.SessionKey;

import com.redrock.elan.common.project.mgm.dto.security.SecUserDto;


public class UserContextInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//设置用户上下文
		Object obj = request.getSession().getAttribute(SessionKey.LOGIN_ACCOUNT);
		if (obj != null && obj instanceof SecUserDto) {
			UserContextUtil.setSecUserDto((SecUserDto)obj);
		}
		//设置IP
		UserContextUtil.setIP(RequestUtil.getRealRemoteAddr(request));
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && modelAndView.getModel() != null && !modelAndView.getModel().isEmpty()) {
			Iterator<String> iter = modelAndView.getModel().keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				Object obj = modelAndView.getModel().get(key);
				if (key.indexOf("org.springframework.validation.BindingResult") > -1) {
					modelAndView.getModel().put("validate_errros_model_obj",key.substring(key.lastIndexOf(".") + 1));
					if (obj != null && obj instanceof Errors) {
						Errors errors = (Errors) obj;
						modelAndView.getModel().put("validate_errros_count",errors.getErrorCount() + errors.getGlobalErrorCount());
					} else {
						modelAndView.getModel().put("validate_errros_count", 0);
					}
					break;
				}
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
