package com.redrock.elan.common.project.mgm.app;

import java.util.List;

import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.dto.busi.BusiGoodsDto;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;

public interface BusiGoodsAppService {

	public PageData<BusiGoodsDto> listByCatetory(String category)
			throws MgmCheckedException;

	public PageData<BusiGoodsDto> listWithHasInventory(PageData<BusiGoodsDto> pageData)
			throws MgmCheckedException;

	public BusiGoodsDto findById(String id) throws MgmCheckedException;
	
	public List<BusiGoodsDto> findByIds(List<String> ids);
}
