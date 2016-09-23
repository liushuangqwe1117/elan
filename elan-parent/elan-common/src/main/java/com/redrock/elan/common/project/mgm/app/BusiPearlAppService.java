package com.redrock.elan.common.project.mgm.app;

import java.util.List;

import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.dto.busi.BusiPearlDto;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;

public interface BusiPearlAppService {

	public PageData<BusiPearlDto> listByCatetory(String category)
			throws MgmCheckedException;

	public PageData<BusiPearlDto> listWithHasInventory(PageData<BusiPearlDto> pageData)
			throws MgmCheckedException;

	public BusiPearlDto findById(String id) throws MgmCheckedException;
	
	public List<BusiPearlDto> findByIds(List<String> ids);
}
