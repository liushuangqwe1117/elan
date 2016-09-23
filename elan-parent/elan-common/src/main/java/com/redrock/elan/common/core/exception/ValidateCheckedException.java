/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 * 创建:ZhangDM(Mingly) 2012-12-10
 */

package com.redrock.elan.common.core.exception;

/**
 * 校验失败异常
 * 
 * @author LS
 *
 */
public class ValidateCheckedException extends CodeCheckedException {

	private static final long serialVersionUID = 1L;

	private String code;

	public ValidateCheckedException() {
		super();
	}

	public ValidateCheckedException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public ValidateCheckedException(String code, String message) {
		super(code, message);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
