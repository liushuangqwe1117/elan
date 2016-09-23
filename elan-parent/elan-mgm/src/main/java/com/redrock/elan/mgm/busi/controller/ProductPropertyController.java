package com.redrock.elan.mgm.busi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.ylinkpay.framework.core.model.PageData;
import org.ylinkpay.framework.web.base.util.PageMessageUtil;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.core.converter.EProductPropertyConverter;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;
import com.redrock.elan.mgm.busi.service.BusiProductPropertyService;
import com.redrock.elan.mgm.common.BaseController;

@Controller
@RequestMapping("/productproperty")
public class ProductPropertyController extends BaseController {

	private static final String REQUEST_BASE_PATH = "/productproperty/";
	private static final String PAGE_BASE_PATH = "busi/base/";
	private static final String LIST_PATH = PAGE_BASE_PATH
			+ "productproperty_list";
	private static final String EDIT_PATH = PAGE_BASE_PATH
			+ "productproperty_edit";

	@Autowired
	BusiProductPropertyService productPropertyService;

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(EProductProperty.class,
				new EProductPropertyConverter());
	}

	@RequestMapping("/list")
	public String list(
			Model model,
			PageData<BusiProductPropertyDto> pageData,
			@RequestParam(value = "type", required = false) EProductProperty type,
			HttpServletRequest request) {
		pageData = productPropertyService.listPage(pageData, type);
		setPagination(model, pageData, request);

		model.addAttribute("ppes", EProductProperty.values());

		return LIST_PATH;
	}

	@RequestMapping("/edit")
	public String edit(Model model,
			@RequestParam(value = "id", required = false) String id) {
		BusiProductPropertyDto pp = null;
		if (StringUtils.isNotBlank(id)) {
			pp = productPropertyService.findById(id);
		} else {
			pp = new BusiProductPropertyDto();
		}
		model.addAttribute("obj", pp);
		model.addAttribute("ppes", EProductProperty.getConfigProductProperty());
		return EDIT_PATH;
	}

	@RequestMapping("/save")
	public String save(Model model, BusiProductPropertyDto pp,HttpSession session) {
		if (StringUtils.isNotBlank(pp.getId())) {
			productPropertyService.updateSelective(pp);
		} else {
			productPropertyService.save(pp);
		}
		PageMessageUtil.saveSuccessMessage(session, "保存产品属性成功");
		return redirectToList();
	}

	@RequestMapping("/delete")
	public String delete(Model model,@RequestParam("id") String id,HttpSession session) {
		int count = productPropertyService.queryRef(id);
		if(count > 0) {
			PageMessageUtil.saveErrorMessage(session, "该产品属性已被引用，不能删除");
		} else {
			productPropertyService.delete(id);
			PageMessageUtil.saveSuccessMessage(session, "删除成功");
		}
		return redirectToList();
	}
	
	public String redirectToList(){
		return "redirect:" + REQUEST_BASE_PATH + "list.html";
	}
}
