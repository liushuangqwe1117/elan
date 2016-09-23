package com.redrock.elan.mgm.system.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class FoundUserErrorException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FoundUserErrorException() {
		super();
	}

	public FoundUserErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public FoundUserErrorException(String message) {
		super(message);
	}

	public FoundUserErrorException(Throwable cause) {
		super(cause);
	}

}
