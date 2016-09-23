/**
 * 版权所有(C) 2014 深圳市雁联计算系统有限公司
 * 创建:LS 2014-2-17
 */

package com.redrock.elan.mgm.security.common;

/** 
 * @author LS
 * @date 2014-2-17
 * @description：TODO
 */
public enum EChangePwdError {

	OLD_PWD_EMPTY("01","原密码不能为空"),
	NEW_PWD_EMPTY("02","新密码不能为空"),
	NEW_REPWD_EMPTY("03","重复新密码不能为空"),
	PWD_REPWD_NO_EQUAL("04","'重复新密码'必须与'新密码'相同！"),
	OLDPWD_NEWPWD_EQUAL("05","'新密码'不能与'原密码'相同！"),
	OLD_PWD_ERROR("06","原密码不正确，不能修改密码！"),
	ERROR("10","更新密码失败"),
	SUCCESS("11","更新密码成功");
	
	private String value;
	private String msg;
	
	private EChangePwdError(String value,String msg){
		this.value = value;
		this.msg = msg;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getMsg(){
		return this.msg;
	}
}
