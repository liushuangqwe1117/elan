package com.redrock.elan.common.core.dto;

public class KeyValueDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -678710263704572873L;

	private String key;
	private String value;

	public KeyValueDto() {
	}

	public KeyValueDto(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
