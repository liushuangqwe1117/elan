package com.redrock.elan.mgm.busi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.redrock.elan.common.project.mgm.dto.busi.BusiFileDto;
import com.redrock.elan.common.project.mgm.exception.MgmUncheckedException;
import com.redrock.elan.mgm.busi.dao.BusiFileDtoMapper;
import com.redrock.elan.mgm.util.PropertyUtil;

@Service("busiFileService")
public class BusiFileServiceImpl implements BusiFileService {
	
	private Logger _log = LoggerFactory.getLogger(BusiFileServiceImpl.class);

	@Autowired
	BusiFileDtoMapper busiFileDtoMapper;
	
	@Override
	@Transactional
	public String save(MultipartFile file) throws MgmUncheckedException {
		if(file != null && file.getSize() >0) {
			String fileName = file.getOriginalFilename();
			String ext = getFileExt(fileName);
			BusiFileDto fileDto = new BusiFileDto();
			fileDto.setId(fileDto.getIdentity());
			fileDto.setFileName(fileName);
			fileDto.setFileSize(file.getSize());
			fileDto.setFileExt(ext);
			fileDto.setFileLocation("");
			fileDto.setFileType(file.getContentType());
			
			try {
				File desFile = new File(PropertyUtil.getFileBaseDir()+fileDto.getId()+ext);
				if(!desFile.exists()) {
					desFile.mkdirs();
				}
				//存盘
				file.transferTo(desFile);
				//存数据库
				busiFileDtoMapper.insert(fileDto);
				return fileDto.getId();
			} catch (IOException e) {
				_log.error(e.getMessage(),e);
				throw new MgmUncheckedException("","文件上传文件失败");
			}
		}
		return null;
	}
	private String getFileExt(String fileName) {
		if(StringUtils.isNotBlank(fileName)) {
			int idx = fileName.lastIndexOf(".");
			if(idx > -1) {
				return fileName.substring(idx);
			}
		}
		return "";
	}

	@Override
	public BusiFileDto getById(String id) {
		BusiFileDto fileDto = busiFileDtoMapper.selectByPrimaryKey(id);
		if(fileDto != null) {
			File file = new File(PropertyUtil.getFileBaseDir()+fileDto.getId()+fileDto.getFileExt());
			byte [] buffer = new byte[fileDto.getFileSize().intValue()];
			FileInputStream inputStream = null;
			try {
				inputStream = new FileInputStream(file);
				IOUtils.read(inputStream, buffer);
				fileDto.setFileContent(buffer);
				return fileDto;
			} catch (Exception e) {
				_log.error(e.getMessage(),e);
				throw new MgmUncheckedException("","获取文件失败");
			} finally {
				IOUtils.closeQuietly(inputStream);
			}
		}
		return null;
	}

}