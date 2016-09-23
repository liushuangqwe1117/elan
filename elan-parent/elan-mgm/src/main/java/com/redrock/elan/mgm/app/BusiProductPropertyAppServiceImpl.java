package com.redrock.elan.mgm.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.project.mgm.app.BusiProductPropertyAppService;
import com.redrock.elan.common.project.mgm.dto.busi.BusiFileDto;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;
import com.redrock.elan.mgm.busi.service.BusiFileService;
import com.redrock.elan.mgm.busi.service.BusiProductPropertyService;

@Service("busiProductPropertyAppService")
public class BusiProductPropertyAppServiceImpl implements
		BusiProductPropertyAppService {
	
	@Autowired
	BusiProductPropertyService busiProductPropertyService;

	@Autowired
	BusiFileService busiFileService;
	
	
	@Override
	public List<BusiProductPropertyDto> listByTypes(List<EProductProperty> typeList)
			throws MgmCheckedException {
		return busiProductPropertyService.listByTypes(typeList);
	}

	@Override
	public BusiFileDto getFileById(String fileId) throws MgmCheckedException {
		return busiFileService.getById(fileId);
	}

}
