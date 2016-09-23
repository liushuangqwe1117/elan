package com.redrock.elan.portal.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CompanyRejectedException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyRejectedException() {
		super();
	}

	public CompanyRejectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyRejectedException(String message) {
		super(message);
	}

	public CompanyRejectedException(Throwable cause) {
		super(cause);
	}

}
