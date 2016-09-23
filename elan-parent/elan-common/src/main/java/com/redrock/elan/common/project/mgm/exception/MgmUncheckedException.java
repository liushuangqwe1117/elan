package com.redrock.elan.common.project.mgm.exception;

import com.redrock.elan.common.core.exception.CodeUncheckException;


public class MgmUncheckedException extends CodeUncheckException {

	private static final long serialVersionUID = 1L;

	public MgmUncheckedException() {
		super();
	}

	public MgmUncheckedException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public MgmUncheckedException(String code, String message) {
		super(code, message);
	}

}
