package com.redrock.elan.portal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.project.mgm.app.BusiProductPropertyAppService;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;

public abstract class AbstractController {
	Logger _log = LoggerFactory.getLogger(getClass());

	@Autowired
	BusiProductPropertyAppService busiProductPropertyAppService;
	
	protected static final int INDEX_PAGE_SIZE = 6;
	protected static final int CATEGORY_PAGE_SIZE = 15;
	private static Map<String, BusiProductPropertyDto> bppDataMap = new HashMap<String, BusiProductPropertyDto>();

	public static BusiProductPropertyDto getBppData(String key) {
		return bppDataMap.get(key);
	}

	private BusiProductPropertyDto getPearBusiProductProperty() {
		BusiProductPropertyDto pearBpp = new BusiProductPropertyDto();
		pearBpp.setId(EProductProperty.PEARL_CATEGORY.getValue());
		pearBpp.setName("珍珠");
		pearBpp.setType(EProductProperty.PEARL_CATEGORY);

		bppDataMap.put(pearBpp.getType().getValue() + pearBpp.getId(), pearBpp);

		return pearBpp;
	}

	protected List<BusiProductPropertyDto> getTopNavigate() {
		List<BusiProductPropertyDto> accessorsList = new ArrayList<BusiProductPropertyDto>();
		accessorsList.add(getPearBusiProductProperty());

		List<EProductProperty> typeList = new ArrayList<EProductProperty>();
		typeList.add(EProductProperty.ACCESSORY_CATEGORY);
		typeList.add(EProductProperty.GOODS_CATEGORY);
		try {
			List<BusiProductPropertyDto> items = busiProductPropertyAppService
					.listByTypes(typeList);
			if (items != null && !items.isEmpty()) {
				List<BusiProductPropertyDto> goodsList = new ArrayList<BusiProductPropertyDto>();

				for (BusiProductPropertyDto bpp : items) {
					bppDataMap.put(bpp.getType().getValue() + bpp.getId(), bpp);

					if (bpp.getType() == EProductProperty.ACCESSORY_CATEGORY) {
						accessorsList.add(bpp);
					} else if (bpp.getType() == EProductProperty.GOODS_CATEGORY) {
						goodsList.add(bpp);
					}
				}

				for (BusiProductPropertyDto bpp : goodsList) {
					accessorsList.add(bpp);
				}
			}
		} catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
		return accessorsList;
	}
}
