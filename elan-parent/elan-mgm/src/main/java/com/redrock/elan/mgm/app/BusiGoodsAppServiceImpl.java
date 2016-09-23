package com.redrock.elan.mgm.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.app.BusiGoodsAppService;
import com.redrock.elan.common.project.mgm.dto.busi.BusiGoodsDto;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;
import com.redrock.elan.mgm.busi.service.BusiGoodsService;

@Service("busiGoodsAppService")
public class BusiGoodsAppServiceImpl implements BusiGoodsAppService {
	
	@Autowired
	BusiGoodsService busiGoodsService;

	@Override
	public PageData<BusiGoodsDto> listByCatetory(String category)
			throws MgmCheckedException {
		PageData<BusiGoodsDto> pageData = new PageData<BusiGoodsDto>();
		BusiGoodsDto queryParam = new BusiGoodsDto();
		queryParam.setCategory(category);
		return busiGoodsService.listPage(pageData, queryParam);
	}

	@Override
	public PageData<BusiGoodsDto> listWithHasInventory(PageData<BusiGoodsDto> pageData)
			throws MgmCheckedException {
		BusiGoodsDto queryParam = new BusiGoodsDto();
		queryParam.setZeroNoShow(Boolean.TRUE);
		return busiGoodsService.listPage(pageData, queryParam);
	}

	@Override
	public BusiGoodsDto findById(String id) throws MgmCheckedException {
		return busiGoodsService.findById(id);
	}

	@Override
	public List<BusiGoodsDto> findByIds(List<String> ids) {
		return busiGoodsService.findByIds(ids);
	}

}
