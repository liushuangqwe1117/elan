package com.redrock.elan.mgm.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redrock.elan.common.core.constant.ERoleType;
import com.redrock.elan.common.project.mgm.dto.security.SecPermDto;
import com.redrock.elan.mgm.security.dao.SecPermDtoMapper;

@Service("secPermService")
public class SecPermServiceImpl implements SecPermService {

	@Autowired
	SecPermDtoMapper secPermDtoMapper;
	
	@Override
	public List<String> listPermID() {
		return secPermDtoMapper.listPermID();
	}

	@Override
	public List<SecPermDto> listAll() {
		return secPermDtoMapper.listAll();
	}

	@Override
	@Transactional
	public void updateRolePerms(List<String> perms, String role) {
		secPermDtoMapper.deleteByRole(role);
		secPermDtoMapper.updateRolePerms(perms, role);
	}

	@Override
	@Transactional
	public void initAdminRole() {
		updateRolePerms(listPermID(), ERoleType.ADMIN.getValue());
	}

	@Override
	public List<String> findPermIDByRole(String role) {
		return secPermDtoMapper.findPermIDByRole(role);
	}
}
