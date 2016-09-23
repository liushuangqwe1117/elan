package com.redrock.elan.common.core.constant;

import java.util.HashMap;
import java.util.Map;

public enum ERoleType implements BaseEnum<ERoleType, String> {
	ADMIN("ADMIN", "管理员");

	private String value;
	private String displayName;

	ERoleType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	static Map<String, ERoleType> dataMap = new HashMap<String, ERoleType>();
	static {
		for (ERoleType roleType : values()) {
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

	public static ERoleType[] getAllowRoleType() {
		return new ERoleType[] { ERoleType.ADMIN};
	}

	public static ERoleType getEnum(String value) {
		return dataMap.get(value);
	}
}
