package com.redrock.elan.common.core.constant;

import java.util.HashMap;
import java.util.Map;

public enum EAccessoryCategory implements BaseEnum<EAccessoryCategory, String> {
	PENDANT("PENDANT", "吊坠配件"), 
	NECKLACE("NECKLACE", "项链配件"), 
	EARDROP("EARDROP", "耳坠配件"),
	CORSAGE("CORSAGE", "胸花配件")
	;
	private String value;
	private String displayName;

	EAccessoryCategory(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	static Map<String, EAccessoryCategory> dataMap = new HashMap<String, EAccessoryCategory>();
	static {
		for (EAccessoryCategory roleType : values()) {
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

	public static EAccessoryCategory getEnum(String value) {
		return dataMap.get(value);
	}
}
