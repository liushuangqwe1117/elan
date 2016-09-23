package com.redrock.elan.common.project.mgm.dto.busi;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.core.dto.IdentityDto;

public class BusiProductPropertyDto extends IdentityDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5014779760643706674L;

	/**
	 * 主键
	 *
	 * @mbggenerated Sun Aug 28 10:30:06 CST 2016
	 */
	private String id;

	/**
	 * 类型
	 *
	 * @mbggenerated Sun Aug 28 10:30:06 CST 2016
	 */
	private EProductProperty type;

	/**
	 * 名称
	 *
	 * @mbggenerated Sun Aug 28 10:30:06 CST 2016
	 */
	private String name;

	private Integer nord;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public EProductProperty getType() {
		return type;
	}

	public void setType(EProductProperty type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getNord() {
		return nord;
	}

	public void setNord(Integer nord) {
		this.nord = nord;
	}

}