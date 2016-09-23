package com.redrock.elan.common.project.mgm.app;

import java.util.List;

import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.dto.busi.BusiAccessoryDto;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;

public interface BusiAccessoryAppService {

	public PageData<BusiAccessoryDto> listByCatetory(String category)
			throws MgmCheckedException;

	public PageData<BusiAccessoryDto> listByCatetoryWithHasInventory(
			PageData<BusiAccessoryDto> pageData, String category)
			throws MgmCheckedException;

	public BusiAccessoryDto findById(String id) throws MgmCheckedException;
	
	public List<BusiAccessoryDto> findByIds(List<String> ids);
}
