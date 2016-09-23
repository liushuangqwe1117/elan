package com.redrock.elan.mgm.busi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ylinkpay.framework.mybatis.annotation.MybatisMapper;

import com.redrock.elan.common.project.mgm.dto.busi.BusiGoodsDto;

@MybatisMapper("busiGoodsDtoMapper")
public interface BusiGoodsDtoMapper {
	int deleteByPrimaryKey(String id);

	int insert(BusiGoodsDto record);

	int insertSelective(BusiGoodsDto record);

	BusiGoodsDto selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(BusiGoodsDto record);

	int updateByPrimaryKey(BusiGoodsDto record);
	
	List<BusiGoodsDto> list(BusiGoodsDto queryParam);
	
	List<BusiGoodsDto> findByIds(@Param("ids")List<String> ids);
}