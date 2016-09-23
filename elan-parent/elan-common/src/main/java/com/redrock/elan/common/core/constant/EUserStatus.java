package com.redrock.elan.common.core.constant;

import java.util.HashMap;
import java.util.Map;

public enum EUserStatus implements BaseEnum<EUserStatus, String> {
	WAIT_ACTIVE("WAIT_ACTIVE", "待激活"), 
	EFFECTIVE("EFFECTIVE", "有效"), 
	DISABLED("DISABLED", "停用"),
	DELETE("DELETE", "注销")
	;
	private String value;
	private String displayName;

	EUserStatus(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	static Map<String, EUserStatus> dataMap = new HashMap<String, EUserStatus>();
	static {
		for (EUserStatus roleType : values()) {
			dataMap.put(roleType.getValue(), roleType);
		}
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	public static EUserStatus getEnum(String value) {
		return dataMap.get(value);
	}
}
