package com.redrock.elan.mgm.busi.service;

import java.util.List;

import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.dto.busi.BusiPearlDto;;

public interface BusiPearlService {
	public void save(BusiPearlDto accessoryDto);

	public void updateSelective(BusiPearlDto accessoryDto);

	public PageData<BusiPearlDto> listPage(
			PageData<BusiPearlDto> pageData, BusiPearlDto queryParam);

	public BusiPearlDto findById(String id);
	
	public void delete(String id);
	
	public List<BusiPearlDto> findByIds(List<String> ids);
}