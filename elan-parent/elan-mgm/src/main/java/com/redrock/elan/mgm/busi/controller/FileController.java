package com.redrock.elan.mgm.busi.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.redrock.elan.common.project.mgm.dto.busi.BusiFileDto;
import com.redrock.elan.mgm.busi.service.BusiFileService;
import com.redrock.elan.mgm.common.BaseController;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

	@Autowired
	BusiFileService busiFileService;
	
	@RequestMapping("/download")
	public void download(@RequestParam("id")String id,HttpServletResponse response) {
		BusiFileDto fileDto = busiFileService.getById(id);
		if(fileDto != null && fileDto.getFileContent() != null){
			response.setCharacterEncoding("utf-8");
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				IOUtils.write(fileDto.getFileContent(), os);
			} catch (IOException e) {
				_log.error(e.getMessage(),e);
			} finally {
				IOUtils.closeQuietly(os);
			}
		}
	}
}
