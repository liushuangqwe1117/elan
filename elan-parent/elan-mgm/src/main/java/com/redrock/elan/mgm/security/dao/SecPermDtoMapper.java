package com.redrock.elan.mgm.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ylinkpay.framework.mybatis.annotation.MybatisMapper;

import com.redrock.elan.common.project.mgm.dto.security.SecPermDto;

@MybatisMapper("secPermDtoMapper")
public interface SecPermDtoMapper {
	int deleteByPrimaryKey(String id);

	int insert(SecPermDto record);

	int insertSelective(SecPermDto record);

	SecPermDto selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SecPermDto record);

	int updateByPrimaryKey(SecPermDto record);

	List<String> listPermID();

	List<SecPermDto> listAll();

	void updateRolePerms(@Param("perms") List<String> perms,
			@Param("role") String role);
	
	void deleteByRole(String role);
	
	List<String> findPermIDByRole(String role);
}