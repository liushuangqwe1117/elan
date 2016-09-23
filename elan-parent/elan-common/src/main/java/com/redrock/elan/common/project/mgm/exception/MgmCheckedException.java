package com.redrock.elan.common.project.mgm.exception;

import com.redrock.elan.common.core.exception.CodeCheckedException;


public class MgmCheckedException extends CodeCheckedException {

	private static final long serialVersionUID = 1L;

	public MgmCheckedException() {
		super();
	}

	public MgmCheckedException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public MgmCheckedException(String code, String message) {
		super(code, message);
	}

}
