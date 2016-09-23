package com.redrock.elan.mgm.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.core.util.page.PaginationInfo;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;
import com.redrock.elan.mgm.busi.service.BusiProductPropertyService;

public abstract class BaseController {

	protected Logger _log = LoggerFactory.getLogger(getClass());

	@Autowired
	BusiProductPropertyService productPropertyService;
	
	/**
	 * 分页控件
	 * 
	 * @param model
	 * @param pageData
	 * @param request
	 */
	protected void setPagination(Model model, PageData<?> pageData,
			HttpServletRequest request) {

		Enumeration<?> enums = request.getParameterNames();
		StringBuilder sb = new StringBuilder();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			if ("t".equals(key) || "pageNumber".equals(key)
					|| "pageSize".equals(key)) {
				continue;
			}
			sb.append("&" + key + "=" + request.getParameter(key));
		}
		model.addAttribute("pagination", new PaginationInfo(pageData));
		try {
			model.addAttribute("paginationQueryParam", new String(sb.toString()
					.getBytes(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			_log.error("", e);
		}
	}

	public String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	public Map<EProductProperty,List<BusiProductPropertyDto>> getProductPropertyMap(List<EProductProperty> typeList){
		List<BusiProductPropertyDto> items = productPropertyService.listByTypes(typeList);
		Map<EProductProperty,List<BusiProductPropertyDto>> dataMap = new HashMap<EProductProperty, List<BusiProductPropertyDto>>();
		List<BusiProductPropertyDto> tempList = null;
		if(items != null && !items.isEmpty()) {
			for(BusiProductPropertyDto bpp : items){
				tempList = dataMap.get(bpp.getType());
				if(tempList == null) {
					tempList = new ArrayList<BusiProductPropertyDto>();
					 dataMap.put(bpp.getType(), tempList);
				}
				tempList.add(bpp);
			}
		}
		return dataMap;
	}
}
