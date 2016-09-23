package com.redrock.elan.portal.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.redrock.elan.common.project.mgm.app.BusiProductPropertyAppService;
import com.redrock.elan.common.project.mgm.dto.busi.BusiFileDto;

@Controller
@RequestMapping("/file")
public class FileController extends AbstractController {

	@Autowired
	BusiProductPropertyAppService busiProductPropertyAppService;

	@RequestMapping("/download")
	public void download(@RequestParam("id") String id,
			HttpServletResponse response) {
		BusiFileDto fileDto = null;
		OutputStream os = null;
		try {
			fileDto = busiProductPropertyAppService.getFileById(id);
			if (fileDto != null && fileDto.getFileContent() != null) {
				response.setCharacterEncoding("utf-8");
				os = response.getOutputStream();
				IOUtils.write(fileDto.getFileContent(), os);
			}
		} catch (IOException e) {
			_log.error(e.getMessage(), e);
		} catch (Exception e) {
			_log.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(os);
		}
	}
}
