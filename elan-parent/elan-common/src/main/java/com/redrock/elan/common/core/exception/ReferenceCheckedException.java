package com.redrock.elan.common.core.exception;

/**
 * 数据库依赖异常
 * 
 * <pre>
 * 针对外键依赖，通过程序判断依赖抛出异常
 * </pre>
 * 
 * @author LS
 *
 */
public class ReferenceCheckedException extends CodeCheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2719620530957791490L;

	public ReferenceCheckedException() {
		super();
	}

	public ReferenceCheckedException(String code, String message,
			Throwable cause) {
		super(code, message, cause);
	}

	public ReferenceCheckedException(String code, String message) {
		super(code, message);
	}
}
