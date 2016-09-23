package com.redrock.elan.common.project.mgm.app;

import java.util.List;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.project.mgm.dto.busi.BusiFileDto;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;

public interface BusiProductPropertyAppService {

	public List<BusiProductPropertyDto> listByTypes(
			List<EProductProperty> typeList) throws MgmCheckedException;

	public BusiFileDto getFileById(String fileId) throws MgmCheckedException;
}
