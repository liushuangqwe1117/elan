package com.redrock.elan.mgm.busi.service;

import java.util.List;

import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.dto.busi.BusiAccessoryDto;

public interface BusiAccessoryService {

	public void save(BusiAccessoryDto accessoryDto);

	public void updateSelective(BusiAccessoryDto accessoryDto);

	public PageData<BusiAccessoryDto> listPage(
			PageData<BusiAccessoryDto> pageData, BusiAccessoryDto queryParam);

	public BusiAccessoryDto findById(String id);

	public void delete(String id);
	
	public List<BusiAccessoryDto> findByIds(List<String> ids);
}
