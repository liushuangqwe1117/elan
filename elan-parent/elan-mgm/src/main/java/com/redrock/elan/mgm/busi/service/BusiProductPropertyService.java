package com.redrock.elan.mgm.busi.service;

import java.util.List;

import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;

public interface BusiProductPropertyService {

	public List<BusiProductPropertyDto> listByType(EProductProperty type);
	
	public List<BusiProductPropertyDto> listByTypes(List<EProductProperty> typeList);

	public PageData<BusiProductPropertyDto> listPage(
			PageData<BusiProductPropertyDto> pageData, EProductProperty type);
	
	public void save(BusiProductPropertyDto productProperty);
	
	public void updateSelective(BusiProductPropertyDto productProperty);
	
	public BusiProductPropertyDto findById(String id);
	
	public void delete(String id);
	
	public int queryRef(String id);
}