package com.redrock.elan.common.core.constant;

import java.util.HashMap;
import java.util.Map;

public enum EProduct implements BaseEnum<EProduct, String> {
	NECKLACE("NECKLACE", "项链"), 
	EARRINGS("EARRINGS", "耳环"),
	CORSAGE("CORSAGE", "胸花")
	;
	private String value;
	private String displayName;

	EProduct(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	static Map<String, EProduct> dataMap = new HashMap<String, EProduct>();
	static {
		for (EProduct roleType : values()) {
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

	public static EProduct getEnum(String value) {
		return dataMap.get(value);
	}
}
