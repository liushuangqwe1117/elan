package com.redrock.elan.mgm.busi.service;

import java.util.List;

import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.dto.busi.BusiGoodsDto;

public interface BusiGoodsService {
	public void save(BusiGoodsDto accessoryDto);

	public void updateSelective(BusiGoodsDto accessoryDto);

	public PageData<BusiGoodsDto> listPage(
			PageData<BusiGoodsDto> pageData, BusiGoodsDto queryParam);

	public BusiGoodsDto findById(String id);
	
	public void delete(String id);
	
	public List<BusiGoodsDto> findByIds(List<String> ids);
}