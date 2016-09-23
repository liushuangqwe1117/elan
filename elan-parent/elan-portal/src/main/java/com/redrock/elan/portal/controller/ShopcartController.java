package com.redrock.elan.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.project.mgm.app.BusiAccessoryAppService;
import com.redrock.elan.common.project.mgm.app.BusiGoodsAppService;
import com.redrock.elan.common.project.mgm.app.BusiPearlAppService;

@Controller
@RequestMapping("/shopcart")
public class ShopcartController extends AbstractController {

	@Autowired
	BusiGoodsAppService busiGoodsAppService;

	@Autowired
	BusiPearlAppService busiPearlAppService;

	@Autowired
	BusiAccessoryAppService busiAccessoryAppService;

	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			// 珍珠
			String pearId = "";
			// 配件
			List<String> accessoryList = new ArrayList<String>();
			// 成品
			List<String> goodsList = new ArrayList<String>();
			for (Cookie cookie : cookies) {
				if (cookie.getValue() != null && !"".equals(cookie.getValue())) {
					String[] vals = cookie.getValue().split("-");
					if(vals.length !=2)continue;
					
					if ("1".equals(vals[1])) {
						if (cookie.getName().equalsIgnoreCase(
								EProductProperty.PEARL_CATEGORY.getValue())) {
							pearId = vals[0];
						} else if (cookie.getName().startsWith(
								EProductProperty.ACCESSORY_CATEGORY.getValue())) {
							accessoryList.add(vals[0]);
						} else if (cookie.getName().startsWith(
								EProductProperty.GOODS_CATEGORY.getValue())) {
							goodsList.add(vals[0]);
						}
					}
				}
			}
			// 获取对象
			if (!"".equals(pearId)) {
				try {
					model.addAttribute("pearObj", busiPearlAppService.findById(pearId));
				} catch (Exception e) {
					_log.error(e.getMessage());
				}
			}
			if(!accessoryList.isEmpty()) {
				try {
					model.addAttribute("accessoryList", busiAccessoryAppService.findByIds(accessoryList));
				} catch (Exception e) {
					_log.error(e.getMessage());
				}
			}
			if(!goodsList.isEmpty()) {
				try {
					model.addAttribute("goodsList", busiGoodsAppService.findByIds(goodsList));
				} catch (Exception e) {
					_log.error(e.getMessage());
				}
			}
		}

		return "shopcart/shopcart";
	}

}
