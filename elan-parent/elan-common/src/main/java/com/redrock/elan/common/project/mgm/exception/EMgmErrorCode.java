package com.redrock.elan.common.project.mgm.exception;

import com.redrock.elan.common.core.constant.BaseEnum;

public enum EMgmErrorCode implements BaseEnum<EMgmErrorCode, String> {
	SYS_ERROR("MGM-999", "系统异常");

	private String value;
	private String displayName;

	EMgmErrorCode(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}
}
