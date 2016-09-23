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
import com.redrock.elan.common.project.mgm.dto.busi.BusiGoodsDto;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;
import com.redrock.elan.common.project.mgm.exception.MgmUncheckedException;
import com.redrock.elan.mgm.busi.service.BusiFileService;
import com.redrock.elan.mgm.busi.service.BusiGoodsService;
import com.redrock.elan.mgm.common.BaseController;

@Controller
@RequestMapping("/goods")
public class BusiGoodsController extends BaseController {

	private static final String REQUEST_BASE_PATH = "/goods/";
	private static final String PAGE_PATH = "busi/goods/";
	private static final String LIST_PATH = PAGE_PATH + "goods_list";
	private static final String EDIT_PATH = PAGE_PATH + "goods_edit";
	private static final String VIEW_PATH = PAGE_PATH + "goods_view";

	@Autowired
	BusiGoodsService busiGoodsService;
	
	@Autowired
	BusiFileService busiFileService;

	@RequestMapping("/list")
	public String list(Model model, PageData<BusiGoodsDto> pageData,
			BusiGoodsDto queryParam, HttpServletRequest request) {
		pageData = busiGoodsService.listPage(pageData, queryParam);
		setPagination(model, pageData, request);

		addProperties(model);
		
		return LIST_PATH;
	}

	private void addProperties(Model model){
		Map<EProductProperty, List<BusiProductPropertyDto>> dataMap = getProductPropertyMap(EProductProperty
				.getGoodsProductProperty());
		model.addAttribute("goodsCategorys", dataMap.get(EProductProperty.GOODS_CATEGORY));
		model.addAttribute("goodsStyles", dataMap.get(EProductProperty.GOODS_STYLE));
	}
	
	@RequestMapping("/edit")
	public String edit(Model model , @RequestParam(value = "id",required=false)String id) {
		BusiGoodsDto obj = null;
		if(StringUtils.isNotBlank(id)) {
			obj = busiGoodsService.findById(id);
		} else {
			obj = new BusiGoodsDto();
		}
		model.addAttribute("obj", obj);
		addProperties(model);
		
		return EDIT_PATH;
	}

	@RequestMapping("/save")
	public String save(Model model,BusiGoodsDto obj,@RequestParam(value = "file", required = false) MultipartFile file,HttpSession session) {
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
			busiGoodsService.updateSelective(obj);
		} else {
			busiGoodsService.save(obj);
		}
		return redirectToList();
	}

	@RequestMapping("/view")
	public String view(Model model,@RequestParam("id")String id) {
		BusiGoodsDto obj = busiGoodsService.findById(id);
		model.addAttribute("obj", obj);
		return VIEW_PATH;
	}

	@RequestMapping("/delete")
	public String delete(Model model,@RequestParam("id")String id,HttpSession session) {
		busiGoodsService.delete(id);
		PageMessageUtil.saveSuccessMessage(session,"删除成功");
		return redirectToList();
	}
	
	public String redirectToList() {
		return "redirect:" + REQUEST_BASE_PATH +"list.html";
	}
}
