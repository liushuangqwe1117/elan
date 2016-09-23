package com.redrock.elan.common.project.mgm.dto.security;

import java.util.Date;

import com.redrock.elan.common.core.constant.ERoleType;
import com.redrock.elan.common.core.constant.EUserStatus;
import com.redrock.elan.common.core.dto.IdentityDto;

public class SecUserDto extends IdentityDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6197138535143816449L;

	/**
	 * 主键
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String id;

	/**
	 * 用户账号
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String loginName;

	/**
	 * 用户密码
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String password;

	/**
	 * 真实姓名
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String realName;

	/**
	 * 手机号码
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String mobile;

	/**
	 * 座机
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String phone;

	/**
	 * 邮箱
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String email;

	/**
	 * 角色类型：ADMIN-管理员，PROXY-代理商，NORMAL-普通用户
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private ERoleType roleType;

	/**
	 * 状态：WAIT_ACTIVE-待激活，EFFECTIVE-有效,DISABLED-停用,DELETE-注销
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private EUserStatus status;

	/**
	 * 最后一次更新密码时间
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private Date lastUpdatePasswordTime;

	/**
	 * 登录错误次数
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private Long loginErrorTimes;

	/**
	 * 最后一次登录失败锁定时间
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private Date lastLoginLockTime;

	/**
	 * 备注
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private String description;

	/**
	 * 创建时间
	 *
	 * @mbggenerated Sat Aug 27 17:36:53 CST 2016
	 */
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public ERoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(ERoleType roleType) {
		this.roleType = roleType;
	}

	public EUserStatus getStatus() {
		return status;
	}

	public void setStatus(EUserStatus status) {
		this.status = status;
	}

	public Date getLastUpdatePasswordTime() {
		return lastUpdatePasswordTime;
	}

	public void setLastUpdatePasswordTime(Date lastUpdatePasswordTime) {
		this.lastUpdatePasswordTime = lastUpdatePasswordTime;
	}

	public Long getLoginErrorTimes() {
		return loginErrorTimes;
	}

	public void setLoginErrorTimes(Long loginErrorTimes) {
		this.loginErrorTimes = loginErrorTimes;
	}

	public Date getLastLoginLockTime() {
		return lastLoginLockTime;
	}

	public void setLastLoginLockTime(Date lastLoginLockTime) {
		this.lastLoginLockTime = lastLoginLockTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}