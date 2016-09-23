package com.redrock.elan.common.core.converter;

import java.beans.PropertyEditorSupport;

import com.redrock.elan.common.core.constant.ERoleType;

public class ERoleTypeConverter extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		Object obj = getValue();
		if (obj != null) {
			ERoleType rt = (ERoleType) obj;
			return rt.getValue();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(null != text && !"".equals(text.trim())) {
			setValue(ERoleType.getEnum(text.trim()));
		}
	}
}
