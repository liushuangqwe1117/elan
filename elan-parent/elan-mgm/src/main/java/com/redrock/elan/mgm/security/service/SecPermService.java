package com.redrock.elan.mgm.security.service;

import java.util.List;

import com.redrock.elan.common.project.mgm.dto.security.SecPermDto;

public interface SecPermService {

	/**
	 * 获取所有的权限
	 * 
	 * @return
	 * @author LS
	 * @date 2013-7-31
	 */
	List<String> listPermID();

	List<String> findPermIDByRole(String role);
	
	List<SecPermDto> listAll();
	
	void updateRolePerms(List<String> perms,String role);
	
	void initAdminRole();
	
}
