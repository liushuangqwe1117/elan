package com.redrock.elan.mgm.busi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ylinkpay.framework.mybatis.annotation.MybatisMapper;

import com.redrock.elan.common.project.mgm.dto.busi.BusiPearlDto;

@MybatisMapper("busiPearlDtoMapper")
public interface BusiPearlDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(BusiPearlDto record);

    int insertSelective(BusiPearlDto record);

    BusiPearlDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BusiPearlDto record);

    int updateByPrimaryKey(BusiPearlDto record);
    
    List<BusiPearlDto> list(BusiPearlDto queryParam);
    
    List<BusiPearlDto> findByIds(@Param("ids")List<String> ids);
}