package com.redrock.elan.mgm.common.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.redrock.elan.mgm.common.BaseController;

/**
 * Excel 导出Action
 * 
 * @author yu
 * 
 */
public class ExcelExportController extends BaseController {

	/**
	 * 初始话下载响应参数
	 * 
	 * @param fileName
	 *            文件名
	 * @param mimeType
	 *            mime-type类型
	 * @throws UnsupportedEncodingException
	 */
	protected void initDownloadResponse(HttpServletRequest request,HttpServletResponse response,
			String fileName, String mimeType)
			throws UnsupportedEncodingException {
		String finalFileName = null;
		final String userAgent = request.getHeader("USER-AGENT");
		if (StringUtils.contains(userAgent, "MSIE")) {// IE浏览器
			finalFileName = URLEncoder.encode(fileName, "UTF8");
		} else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
			finalFileName = new String(fileName.getBytes(), "ISO8859-1");
		} else {
			finalFileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition",
				"attachment;filename=\"" + finalFileName + "\"");
	}

}
