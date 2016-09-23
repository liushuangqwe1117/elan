package com.redrock.elan.mgm.busi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.ylinkpay.framework.core.model.PageData;
import org.ylinkpay.framework.web.base.util.PageMessageUtil;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.project.mgm.dto.busi.BusiAccessoryDto;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;
import com.redrock.elan.common.project.mgm.exception.MgmUncheckedException;
import com.redrock.elan.mgm.busi.service.BusiAccessoryService;
import com.redrock.elan.mgm.busi.service.BusiFileService;
import com.redrock.elan.mgm.common.BaseController;

@Controller
@RequestMapping("/accessory")
public class BusiAccessoryController extends BaseController {

	private static final String REQUEST_BASE_PATH = "/accessory/";
	private static final String PAGE_PATH = "busi/accessory/";
	private static final String LIST_PATH = PAGE_PATH + "accessory_list";
	private static final String EDIT_PATH = PAGE_PATH + "accessory_edit";
	private static final String VIEW_PATH = PAGE_PATH + "accessory_view";

	@Autowired
	BusiAccessoryService busiAccessoryService;
	
	@Autowired
	BusiFileService busiFileService;

	@RequestMapping("/list")
	public String list(Model model, PageData<BusiAccessoryDto> pageData,
			BusiAccessoryDto queryParam, HttpServletRequest request) {
		pageData = busiAccessoryService.listPage(pageData, queryParam);
		setPagination(model, pageData, request);

		addProperties(model);
		
		return LIST_PATH;
	}

	private void addProperties(Model model){
		Map<EProductProperty, List<BusiProductPropertyDto>> dataMap = getProductPropertyMap(EProductProperty
				.getAccessoryProductProperty());
		model.addAttribute("accessoryCategorys", dataMap.get(EProductProperty.ACCESSORY_CATEGORY));
		model.addAttribute("accessoryMeterials", dataMap.get(EProductProperty.ACCESSORY_METERIAL));
		model.addAttribute("accessoryStyles", dataMap.get(EProductProperty.ACCESSORY_STYLE));
		model.addAttribute("accessoryWeights", dataMap.get(EProductProperty.ACCESSORY_WEIGHT));
	}
	
	@RequestMapping("/edit")
	public String edit(Model model , @RequestParam(value = "id",required=false)String id) {
		BusiAccessoryDto obj = null;
		if(StringUtils.isNotBlank(id)) {
			obj = busiAccessoryService.findById(id);
		} else {
			obj = new BusiAccessoryDto();
		}
		model.addAttribute("obj", obj);
		addProperties(model);
		
		return EDIT_PATH;
	}

	@RequestMapping("/save")
	public String save(Model model,BusiAccessoryDto obj,@RequestParam(value = "file", required = false) MultipartFile file,HttpSession session) {
		try {
			String fileId = busiFileService.save(file);
			if(StringUtils.isNotBlank(fileId)) {
				obj.setMaxPic(fileId);
			}
		} catch (MgmUncheckedException e) {
			PageMessageUtil.saveErrorMessage(session,e.getMessage());
			model.addAttribute("obj", obj);
			addProperties(model);
			return EDIT_PATH;
		}
		
		if(StringUtils.isNotBlank(obj.getId())) {
			busiAccessoryService.updateSelective(obj);
		} else {
			busiAccessoryService.save(obj);
		}
		return redirectToList();
	}

	@RequestMapping("/view")
	public String view(Model model,@RequestParam("id")String id) {
		BusiAccessoryDto obj = busiAccessoryService.findById(id);
		model.addAttribute("obj", obj);
		return VIEW_PATH;
	}

	@RequestMapping("/delete")
	public String delete(Model model,@RequestParam("id")String id,HttpSession session) {
		busiAccessoryService.delete(id);
		PageMessageUtil.saveSuccessMessage(session,"删除成功");
		return redirectToList();
	}
	
	public String redirectToList() {
		return "redirect:" + REQUEST_BASE_PATH +"list.html";
	}
}
