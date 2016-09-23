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
import com.redrock.elan.common.project.mgm.dto.busi.BusiPearlDto;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;
import com.redrock.elan.common.project.mgm.exception.MgmUncheckedException;
import com.redrock.elan.mgm.busi.service.BusiFileService;
import com.redrock.elan.mgm.busi.service.BusiPearlService;
import com.redrock.elan.mgm.common.BaseController;

@Controller
@RequestMapping("/pearl")
public class BusiPearlController extends BaseController {

	private static final String REQUEST_BASE_PATH = "/pearl/";
	private static final String PAGE_PATH = "busi/pearl/";
	private static final String LIST_PATH = PAGE_PATH + "pearl_list";
	private static final String EDIT_PATH = PAGE_PATH + "pearl_edit";
	private static final String VIEW_PATH = PAGE_PATH + "pearl_view";

	@Autowired
	BusiPearlService busiPearlService;
	
	@Autowired
	BusiFileService busiFileService;

	@RequestMapping("/list")
	public String list(Model model, PageData<BusiPearlDto> pageData,
			BusiPearlDto queryParam, HttpServletRequest request) {
		pageData = busiPearlService.listPage(pageData, queryParam);
		setPagination(model, pageData, request);

		addProperties(model);
		
		return LIST_PATH;
	}

	private void addProperties(Model model){
		Map<EProductProperty, List<BusiProductPropertyDto>> dataMap = getProductPropertyMap(EProductProperty
				.getPearlProductProperty());
//		model.addAttribute("pearlNames", dataMap.get(EProductProperty.PEARL_NAME));
		model.addAttribute("pearlNlevels", dataMap.get(EProductProperty.PEARL_NLEVEL));
		model.addAttribute("pearlPoints1", dataMap.get(EProductProperty.PEARL_POINT1));
		model.addAttribute("pearlPoints2", dataMap.get(EProductProperty.PEARL_POINT2));
		model.addAttribute("pearlCircles", dataMap.get(EProductProperty.PEARL_CIRCLE));
		model.addAttribute("pearlQualitys", dataMap.get(EProductProperty.PEARL_QUALITY));
		model.addAttribute("pearlNuminositys", dataMap.get(EProductProperty.PEARL_NUMINOSITY));
		model.addAttribute("pearlCategorys", dataMap.get(EProductProperty.PEARL_CATEGORY));
		model.addAttribute("pearlProdPlaces", dataMap.get(EProductProperty.PEARL_PROD_PLACE));
	}
	
	@RequestMapping("/edit")
	public String edit(Model model , @RequestParam(value = "id",required=false)String id) {
		BusiPearlDto obj = null;
		if(StringUtils.isNotBlank(id)) {
			obj = busiPearlService.findById(id);
		} else {
			obj = new BusiPearlDto();
		}
		model.addAttribute("obj", obj);
		addProperties(model);
		
		return EDIT_PATH;
	}

	@RequestMapping("/save")
	public String save(Model model,BusiPearlDto obj,@RequestParam(value = "file", required = false) MultipartFile file,HttpSession session) {
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
			busiPearlService.updateSelective(obj);
		} else {
			busiPearlService.save(obj);
		}
		return redirectToList();
	}

	@RequestMapping("/view")
	public String view(Model model,@RequestParam("id")String id) {
		BusiPearlDto obj = busiPearlService.findById(id);
		model.addAttribute("obj", obj);
		return VIEW_PATH;
	}

	@RequestMapping("/delete")
	public String delete(Model model,@RequestParam("id")String id,HttpSession session) {
		busiPearlService.delete(id);
		PageMessageUtil.saveSuccessMessage(session,"删除成功");
		return redirectToList();
	}
	
	public String redirectToList() {
		return "redirect:" + REQUEST_BASE_PATH +"list.html";
	}
}
