package com.redrock.elan.portal.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码校验异常类
 * 
 * @author LS
 *
 */
public class CheckCodeValidateUncheckedException extends
		AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckCodeValidateUncheckedException() {
	}

	public CheckCodeValidateUncheckedException(String message) {
		super(message);
	}

	public CheckCodeValidateUncheckedException(Throwable cause) {
		super(cause);
	}

	public CheckCodeValidateUncheckedException(String message, Throwable cause) {
		super(message, cause);
	}

}
