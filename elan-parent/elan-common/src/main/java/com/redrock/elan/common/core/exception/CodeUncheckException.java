package com.redrock.elan.common.core.exception;

public class CodeUncheckException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	public CodeUncheckException() {
		super();
	}

	public CodeUncheckException(String code, String message) {
		super(message);
		this.code = code;
	}

	public CodeUncheckException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
