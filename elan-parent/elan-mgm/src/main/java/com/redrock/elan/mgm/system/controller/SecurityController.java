package com.redrock.elan.mgm.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redrock.elan.mgm.common.BaseController;
import com.redrock.elan.mgm.security.service.SecPermService;
import com.redrock.elan.mgm.security.service.SecUserService;

@Controller
public class SecurityController extends BaseController {

	/**
	 * 日志
	 */
	private static Logger _log = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	protected SecUserService securityUserService;
	
	@Autowired
	protected SecPermService secPermService;

	/**
	 * 跳转到用户登录页面
	 * 
	 * @param request
	 * @return
	 * @author LS
	 * @date 2013-7-31
	 */
	@RequestMapping(value = "/login")
	public String login(Model model, HttpServletRequest request) {
		// 如果登录成功(session未失效)，直接返回到主页面
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/main.html";
		} else {
			_log.info("进入登录页面：login/login");
			return "login/login";
		}
	}

	/**
	 * 登录成功跳转
	 * 
	 * @param request
	 * @return
	 * @author LS
	 * @date 2013-7-31
	 */
	@RequestMapping(value = "/main")
	public String main(Model model, HttpSession session) {
		return "layout/main";
	}
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "/home")
	public String home(){
		return "welcome";
	}
	
	/**
	 * 登出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		request.getSession().invalidate();
		return "redirect:/login.html";
	}
}
