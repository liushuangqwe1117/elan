package com.redrock.elan.common.core.constant;

public interface BaseEnum<E extends Enum<?>, T> {
	T getValue();

	String getDisplayName();
}