/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 * 创建:lyg 2012-11-3
 */

package com.redrock.elan.portal.exception;

import org.apache.shiro.authc.AuthenticationException;

/** 
 * @author lyg
 * @date 2012-11-3
 * @description：TODO
 */

public class ShiroException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShiroException(String msg)
	{
		super(msg);
	}
}
