package com.redrock.elan.mgm.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ylinkpay.framework.mybatis.annotation.MybatisMapper;

import com.redrock.elan.common.project.mgm.dto.security.SecUserDto;

@MybatisMapper("secUserDtoMapper")
public interface SecUserDtoMapper {
	int deleteByPrimaryKey(String id);

	int insert(SecUserDto record);

	int insertSelective(SecUserDto record);

	SecUserDto selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SecUserDto record);

	int updateByPrimaryKey(SecUserDto record);

	SecUserDto getByLoginName(String loginName);

	List<SecUserDto> list(SecUserDto queryParam);

	int checkLoginName(@Param("id")String id, @Param("loginName")String loginName);

	int checkEmail(@Param("id")String id, @Param("email")String email);
}