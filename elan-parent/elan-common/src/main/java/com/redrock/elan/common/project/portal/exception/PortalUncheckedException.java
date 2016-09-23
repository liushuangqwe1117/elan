package com.redrock.elan.common.project.portal.exception;

import com.redrock.elan.common.core.exception.CodeUncheckException;

/**
 * 门户异常
 * 
 * @author LS
 *
 */
public class PortalUncheckedException extends CodeUncheckException {

	private static final long serialVersionUID = 1L;

	public PortalUncheckedException() {
		super();
	}

	public PortalUncheckedException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public PortalUncheckedException(String code, String message) {
		super(code, message);
	}

}
