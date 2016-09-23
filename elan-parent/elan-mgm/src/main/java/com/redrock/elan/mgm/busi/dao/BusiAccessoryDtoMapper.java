package com.redrock.elan.mgm.busi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ylinkpay.framework.mybatis.annotation.MybatisMapper;

import com.redrock.elan.common.project.mgm.dto.busi.BusiAccessoryDto;

@MybatisMapper("busiAccessoryDtoMapper")
public interface BusiAccessoryDtoMapper {
	int deleteByPrimaryKey(String id);

	int insert(BusiAccessoryDto record);

	int insertSelective(BusiAccessoryDto record);

	BusiAccessoryDto selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(BusiAccessoryDto record);

	int updateByPrimaryKey(BusiAccessoryDto record);
	
	List<BusiAccessoryDto> list(BusiAccessoryDto queryParam);
	
	List<BusiAccessoryDto> findByIds(@Param("ids")List<String> ids);
}