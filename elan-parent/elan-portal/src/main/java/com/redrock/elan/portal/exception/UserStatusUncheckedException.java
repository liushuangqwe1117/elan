package com.redrock.elan.portal.exception;

import org.apache.shiro.authc.AuthenticationException;

public class UserStatusUncheckedException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserStatusUncheckedException() {
		super();
	}

	public UserStatusUncheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserStatusUncheckedException(String message) {
		super(message);
	}

	public UserStatusUncheckedException(Throwable cause) {
		super(cause);
	}

}
