package com.redrock.elan.portal.exception;

import org.apache.shiro.authc.AuthenticationException;

public class UserLockUncheckedException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserLockUncheckedException() {
		super();
	}

	public UserLockUncheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserLockUncheckedException(String message) {
		super(message);
	}

	public UserLockUncheckedException(Throwable cause) {
		super(cause);
	}

}
