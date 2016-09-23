package com.redrock.elan.common.project.mgm.dto.security;

import com.redrock.elan.common.core.dto.IdentityDto;

public class SecPermDto extends IdentityDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8436495904917860639L;

	/**
	 * 权限主键
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String id;

	/**
	 * 权限名称
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String name;

	/**
	 * 权限级别
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String nlevel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getNlevel() {
		return nlevel;
	}

	public void setNlevel(String nlevel) {
		this.nlevel = nlevel == null ? null : nlevel.trim();
	}
}