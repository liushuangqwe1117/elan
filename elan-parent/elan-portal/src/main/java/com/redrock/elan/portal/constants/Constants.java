package com.redrock.elan.portal.constants;

public final class Constants {
	/*** 保持当前session用户 */
	public static final String SESSION_USER="session_user";
	
	/*** 用户角色 */
	public static String USER_ROLES="user_roles";
	
	/*** 页面显示的错误提示信息 */
	public static final String REQ_ERR_INFO="req_err_info";
	
	/*** 是否检查验证码  true：检查验证码  */
	public static boolean CHECK_CODE_FLAG = true;

	/**
	 * session存放验证码的key值
	 */
	public static final String  SESSION_KEY_CHECK_CODE = com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

	/*** yyyyMMdd年月日格式化 */
	public static final String YYYYMMDD = "yyyyMMdd";
}
