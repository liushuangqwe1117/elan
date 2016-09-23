package com.redrock.elan.common.core.dto;

import org.ylinkpay.framework.core.util.UuidUtil;

public class IdentityDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String identity;

	public String getIdentity() {
		if(this.identity == null || "".equals(this.identity.trim())) {
			this.identity = UuidUtil.getUUID();
		} 
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

}
