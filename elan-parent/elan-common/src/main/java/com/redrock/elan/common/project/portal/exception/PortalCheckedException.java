package com.redrock.elan.common.project.portal.exception;

import com.redrock.elan.common.core.exception.CodeCheckedException;

/**
 * 门户异常
 * 
 * @author LS
 *
 */
public class PortalCheckedException extends CodeCheckedException {

	private static final long serialVersionUID = 1L;

	public PortalCheckedException() {
		super();
	}

	public PortalCheckedException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public PortalCheckedException(String code, String message) {
		super(code, message);
	}

}
