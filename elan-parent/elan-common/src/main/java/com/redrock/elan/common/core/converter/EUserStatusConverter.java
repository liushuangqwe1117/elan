package com.redrock.elan.common.core.converter;

import java.beans.PropertyEditorSupport;

import com.redrock.elan.common.core.constant.EUserStatus;

public class EUserStatusConverter extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		Object obj = getValue();
		if (obj != null) {
			EUserStatus rt = (EUserStatus) obj;
			return rt.getValue();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(null != text && !"".equals(text.trim())) {
			setValue(EUserStatus.getEnum(text.trim()));
		}
	}
}
