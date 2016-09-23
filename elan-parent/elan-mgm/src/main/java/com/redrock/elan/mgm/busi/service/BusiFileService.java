package com.redrock.elan.mgm.busi.service;

import org.springframework.web.multipart.MultipartFile;

import com.redrock.elan.common.project.mgm.dto.busi.BusiFileDto;
import com.redrock.elan.common.project.mgm.exception.MgmUncheckedException;

public interface BusiFileService {

	public String save(MultipartFile file) throws MgmUncheckedException;

	public BusiFileDto getById(String id);

}
