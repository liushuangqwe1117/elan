package com.redrock.elan.mgm.busi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ylinkpay.framework.mybatis.annotation.MybatisMapper;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;

@MybatisMapper("busiProductPropertyDtoMapper")
public interface BusiProductPropertyDtoMapper {
	int deleteByPrimaryKey(String id);

	int insert(BusiProductPropertyDto record);

	int insertSelective(BusiProductPropertyDto record);

	BusiProductPropertyDto selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(BusiProductPropertyDto record);

	int updateByPrimaryKey(BusiProductPropertyDto record);

	List<BusiProductPropertyDto> listByType(@Param("type")EProductProperty type);

	List<BusiProductPropertyDto> listByTypes(@Param("typeList")List<EProductProperty> typeList);
	
	Integer queryByRef(String id);
}