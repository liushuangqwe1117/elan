package com.redrock.elan.mgm.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.project.mgm.app.BusiPearlAppService;
import com.redrock.elan.common.project.mgm.dto.busi.BusiPearlDto;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;
import com.redrock.elan.mgm.busi.service.BusiPearlService;

@Service("busiPearlAppService")
public class BusiPearlAppServiceImpl implements BusiPearlAppService {

	@Autowired
	BusiPearlService busiPearlService;

	@Override
	public PageData<BusiPearlDto> listByCatetory(String category)
			throws MgmCheckedException {
		PageData<BusiPearlDto> pageData = new PageData<BusiPearlDto>();
		BusiPearlDto queryParam = new BusiPearlDto();
		queryParam.setName(category);
		return busiPearlService.listPage(pageData, queryParam);
	}

	@Override
	public PageData<BusiPearlDto> listWithHasInventory(PageData<BusiPearlDto> pageData)
			throws MgmCheckedException {
		BusiPearlDto bpd = new BusiPearlDto();
		bpd.setZeroNoShow(Boolean.TRUE);
		return busiPearlService.listPage(pageData,bpd);
	}

	@Override
	public BusiPearlDto findById(String id) throws MgmCheckedException {
		return busiPearlService.findById(id);
	}

	@Override
	public List<BusiPearlDto> findByIds(List<String> ids) {
		return busiPearlService.findByIds(ids);
	}
}
