package com.redrock.elan.common.core.converter;

import java.beans.PropertyEditorSupport;

import com.redrock.elan.common.core.constant.EProductProperty;

public class EProductPropertyConverter extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		Object obj = getValue();
		if (obj != null) {
			EProductProperty rt = (EProductProperty) obj;
			return rt.getValue();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(null != text && !"".equals(text.trim())) {
			setValue(EProductProperty.getEnum(text.trim()));
		}
	}
}
