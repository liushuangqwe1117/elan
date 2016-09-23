package com.redrock.elan.portal.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.ylinkpay.framework.core.model.PageData;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.core.util.page.PaginationInfo;
import com.redrock.elan.common.project.mgm.app.BusiAccessoryAppService;
import com.redrock.elan.common.project.mgm.app.BusiGoodsAppService;
import com.redrock.elan.common.project.mgm.app.BusiPearlAppService;
import com.redrock.elan.common.project.mgm.dto.busi.BusiAccessoryDto;
import com.redrock.elan.common.project.mgm.dto.busi.BusiGoodsDto;
import com.redrock.elan.common.project.mgm.dto.busi.BusiPearlDto;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;

@Controller
@RequestMapping("/main")
public class MainController extends AbstractController {

	private static final String INDEX_PAGE_PATH = "index/index";
	private static final String CATEGORY_PEAR_PAGE_PATH = "category/pear_category";
	private static final String CATEGORY_ACCESSORY_PAGE_PATH = "category/accessory_category";
	private static final String CATEGORY_GOODS_PAGE_PATH = "category/goods_category";
	
	private static final String DETAIL_PEAR_PAGE_PATH = "detail/pear_detail";
	private static final String DETAIL_ACCESSORY_PAGE_PATH = "detail/accessory_detail";
	private static final String DETAIL_GOODS_PAGE_PATH = "detail/goods_detail";
	
	@Autowired
	BusiGoodsAppService busiGoodsAppService;

	@Autowired
	BusiPearlAppService busiPearlAppService;

	@Autowired
	BusiAccessoryAppService busiAccessoryAppService;

	@RequestMapping("/index")
	public String index(Model model,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "catId", required = false) String categoryId) {
		assembleData(model, type, categoryId, 1,INDEX_PAGE_SIZE);
		return INDEX_PAGE_PATH;
	}

	@RequestMapping("/category")
	public String category(Model model,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "catId", required = false) String categoryId,
			@RequestParam(value = "pageNumber", defaultValue="1") Integer pageNumber) {
		BusiProductPropertyDto curSelectedBpp = assembleData(model, type, categoryId, pageNumber,CATEGORY_PAGE_SIZE);
		return getCategoryPagePath(curSelectedBpp);
	}
	
	private String getCategoryPagePath(BusiProductPropertyDto curSelectedBpp){
		if (curSelectedBpp.getType().equals(
				EProductProperty.PEARL_CATEGORY)) {
			return CATEGORY_PEAR_PAGE_PATH;
		} else if (curSelectedBpp.getType().equals(
				EProductProperty.ACCESSORY_CATEGORY)) {
			return CATEGORY_ACCESSORY_PAGE_PATH;
		} else if (curSelectedBpp.getType().equals(
				EProductProperty.GOODS_CATEGORY)) {
			return CATEGORY_GOODS_PAGE_PATH;
		}
		return null;
	}
	
	private BusiProductPropertyDto assembleData(Model model, String type, String categoryId,int pageNumber,
			int pageSize) {
		List<BusiProductPropertyDto> topNavList = getTopNavigate();
		BusiProductPropertyDto curSelectedBpp = null;
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(categoryId)) {
			curSelectedBpp = getBppData(type + categoryId);
		}
		if (curSelectedBpp == null)
			curSelectedBpp = topNavList.get(0);

		try {
			if (curSelectedBpp.getType().equals(
					EProductProperty.PEARL_CATEGORY)) {
				PageData<BusiPearlDto> pageData = new PageData<BusiPearlDto>();
				pageData.setPageNumber(pageNumber);
				pageData.setPageSize(pageSize);
				pageData = busiPearlAppService.listWithHasInventory(pageData);
				model.addAttribute("pageData", pageData);
				model.addAttribute("pagination", new PaginationInfo(pageData));
			} else if (curSelectedBpp.getType().equals(
					EProductProperty.ACCESSORY_CATEGORY)) {
				PageData<BusiAccessoryDto> pageData = new PageData<BusiAccessoryDto>();
				pageData.setPageNumber(pageNumber);
				pageData.setPageSize(pageSize);
				pageData = busiAccessoryAppService
						.listByCatetoryWithHasInventory(pageData, categoryId);
				model.addAttribute("pageData", pageData);
				model.addAttribute("pagination", new PaginationInfo(pageData));
			} else if (curSelectedBpp.getType().equals(
					EProductProperty.GOODS_CATEGORY)) {
				PageData<BusiGoodsDto> pageData = new PageData<BusiGoodsDto>();
				pageData.setPageNumber(pageNumber);
				pageData.setPageSize(pageSize);
				pageData = busiGoodsAppService.listWithHasInventory(pageData);
				model.addAttribute("pageData", pageData);
				model.addAttribute("pagination", new PaginationInfo(pageData));
			}
		} catch (MgmCheckedException e) {
			_log.error(e.getMessage());
		}
		if(!model.containsAttribute("pageData")) {
			model.addAttribute("pageData", null);
			model.addAttribute("pagination", new PaginationInfo(new PageData<Object>()));
		}

		model.addAttribute("topNavList", topNavList);
		model.addAttribute("curSelectedBpp", curSelectedBpp);
		return curSelectedBpp;
	}
	
	@RequestMapping("/view")
	public String view(Model model,@RequestParam(value = "type") String type,
			@RequestParam(value = "id") String id) {
		if (EProductProperty.PEARL_CATEGORY.getValue().equalsIgnoreCase(type)) {
			BusiPearlDto obj = null;
			try {
				obj = busiPearlAppService.findById(id);
			} catch (Exception e) {
				_log.error(e.getMessage());
			}
			if(obj == null)obj = new BusiPearlDto();
			model.addAttribute("obj", obj);
			return DETAIL_PEAR_PAGE_PATH;
		} else if(EProductProperty.ACCESSORY_CATEGORY.getValue().equalsIgnoreCase(type)){
			BusiAccessoryDto obj = null;
			try {
				obj = busiAccessoryAppService.findById(id);
			} catch (Exception e) {
				_log.error(e.getMessage());
			}
			if(obj == null)obj = new BusiAccessoryDto();
			model.addAttribute("obj", obj);
			return DETAIL_ACCESSORY_PAGE_PATH;
		} else if(EProductProperty.GOODS_CATEGORY.getValue().equalsIgnoreCase(type)){
			BusiGoodsDto obj = null;
			try {
				obj = busiGoodsAppService.findById(id);
			} catch (Exception e) {
				_log.error(e.getMessage());
			}
			if(obj == null)obj = new BusiGoodsDto();
			model.addAttribute("obj", obj);
			return DETAIL_GOODS_PAGE_PATH;
		}
		return null;
	}
}