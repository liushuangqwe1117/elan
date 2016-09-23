package com.redrock.elan.portal.exception;

import org.apache.shiro.authc.AuthenticationException;

public class UserNotFoundUncheckedException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundUncheckedException() {
		super();
	}

	public UserNotFoundUncheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundUncheckedException(String message) {
		super(message);
	}

	public UserNotFoundUncheckedException(Throwable cause) {
		super(cause);
	}

}
