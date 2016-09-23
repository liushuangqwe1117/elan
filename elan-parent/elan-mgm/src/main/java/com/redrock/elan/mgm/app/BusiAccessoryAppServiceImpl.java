package com.redrock.elan.mgm.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.app.BusiAccessoryAppService;
import com.redrock.elan.common.project.mgm.dto.busi.BusiAccessoryDto;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;
import com.redrock.elan.mgm.busi.service.BusiAccessoryService;

@Service("busiAccessoryAppService")
public class BusiAccessoryAppServiceImpl implements BusiAccessoryAppService {
	
	@Autowired
	BusiAccessoryService busiAccessoryService;

	@Override
	public PageData<BusiAccessoryDto> listByCatetory(String category)
			throws MgmCheckedException {
		PageData<BusiAccessoryDto> pageData = new PageData<BusiAccessoryDto>();
		BusiAccessoryDto queryParam = new BusiAccessoryDto();
		queryParam.setCategory(category);
		return busiAccessoryService.listPage(pageData, queryParam);
	}

	@Override
	public BusiAccessoryDto findById(String id) throws MgmCheckedException {
		return busiAccessoryService.findById(id);
	}

	@Override
	public PageData<BusiAccessoryDto> listByCatetoryWithHasInventory(
			PageData<BusiAccessoryDto> pageData, String category)
			throws MgmCheckedException {
		BusiAccessoryDto queryParam = new BusiAccessoryDto();
		queryParam.setCategory(category);
		queryParam.setZeroNoShow(Boolean.TRUE);
		return busiAccessoryService.listPage(pageData, queryParam);
	}

	@Override
	public List<BusiAccessoryDto> findByIds(List<String> ids) {
		return busiAccessoryService.findByIds(ids);
	}

}
