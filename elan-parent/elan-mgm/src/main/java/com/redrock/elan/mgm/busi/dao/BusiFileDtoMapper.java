package com.redrock.elan.mgm.busi.dao;

import org.ylinkpay.framework.mybatis.annotation.MybatisMapper;

import com.redrock.elan.common.project.mgm.dto.busi.BusiFileDto;

@MybatisMapper("busiFileDtoMapper")
public interface BusiFileDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(BusiFileDto record);

    int insertSelective(BusiFileDto record);

    BusiFileDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BusiFileDto record);

    int updateByPrimaryKey(BusiFileDto record);
}