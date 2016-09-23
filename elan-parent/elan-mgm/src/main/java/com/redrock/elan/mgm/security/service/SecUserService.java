package com.redrock.elan.mgm.security.service;

import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.dto.security.SecUserDto;

public interface SecUserService {

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	SecUserDto get(String id);

	/**
	 * 获取登录用户
	 * 
	 * @param loginName
	 * @return
	 * @author LS
	 * @date 2013-7-29
	 */
	SecUserDto getByLoginName(String loginName);

	/**
	 * 查询账户列表
	 * 
	 * @param pageData
	 * @param params
	 * @return
	 * @author LS
	 * @date 2013-8-1
	 */
	PageData<SecUserDto> listPage(PageData<SecUserDto> pageData, SecUserDto params);

	/**
	 * 初始化系统管理员登录账号
	 * 
	 * @author LS
	 * @date 2013-8-6
	 */
	public void initSecUserDto();

	/**
	 * 保存基本信息及角色信息
	 * 
	 * @param entity
	 * @author LS
	 * @date 2013-8-3
	 */
	public void save(SecUserDto entity);

	/**
	 * 更新基本信息及角色信息
	 * 
	 * @param entity
	 * @author LS
	 * @date 2013-8-3
	 */
	public void update(SecUserDto entity);

	/**
	 * 检查登录名是否存在, 存在则返回true，否则返回false
	 * 
	 * @param entity
	 * @return
	 * @author LS
	 * @date 2013-8-3
	 */
	boolean checkLoginName(String id, String loginName);

	/**
	 * 检查邮箱是否存在, 存在则返回true，否则返回false
	 * 
	 * @param entity
	 * @return
	 * @author LS
	 * @date 2013-8-3
	 */
	boolean checkEmail(String id, String email);

	/**
	 * 登录失败锁定时间
	 * 
	 * @param id
	 * @return
	 */
	void updateLockTime(String id);

	/**
	 * 更新非空字段
	 * 
	 * @description
	 * @param entity
	 * @author LS
	 * @date 2014-2-15
	 */
	void updateSelective(SecUserDto entity);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 启用用户
	 * 
	 * @param id
	 */
	void unlock(String id);

	/**
	 * 停用用户
	 * 
	 * @param id
	 */
	void lock(String id);

	/**
	 * 修改密码
	 * 
	 * @description
	 * @param id
	 * @param password
	 * @author LS
	 * @date 2014-2-15
	 */
	void changepwd(String id, String password);
	
	void lockUser(String id);
	
	void loginSuccess(String id);
}
