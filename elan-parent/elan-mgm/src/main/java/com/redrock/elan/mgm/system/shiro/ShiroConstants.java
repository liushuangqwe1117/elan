package com.redrock.elan.mgm.system.shiro;

public interface ShiroConstants {
	/** 是否显示验证码 */
	public static final String CAPTCHA_ENABLED = "captcha.enabled";
	
	/** 登录失败几次后显示验证码*/
	public static final String CAPTCHA_LOGIN_IGNORE_TIMES = "captcha.login.ignore.times";
	
	/** 进行帐户锁定的登录失败次数*/
	public static final String LOGIN_FAILURE_LOCK_TIMES = "login.failure.lock.times";
	
	/** 登录帐户锁定后恢复时间（分钟）*/
	public static final String LOGIN_FAILURE_LOCK_MINUTES = "login.failure.lock.minutes";
}
