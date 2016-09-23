package com.redrock.elan.mgm.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ylinkpay.framework.core.model.PageData;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.redrock.elan.common.core.constant.EProductProperty;
import com.redrock.elan.common.project.mgm.dto.busi.BusiProductPropertyDto;
import com.redrock.elan.mgm.busi.dao.BusiProductPropertyDtoMapper;

@Service("busiProductPropertyService")
public class BusiProductPropertyServiceImpl implements
		BusiProductPropertyService {

	@Autowired
	BusiProductPropertyDtoMapper busiProductPropertyDtoMapper;

	@Override
	public List<BusiProductPropertyDto> listByType(EProductProperty type) {
		return busiProductPropertyDtoMapper.listByType(type);
	}

	@Override
	public PageData<BusiProductPropertyDto> listPage(
			PageData<BusiProductPropertyDto> pageData, EProductProperty type) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<BusiProductPropertyDto> items = busiProductPropertyDtoMapper
				.listByType(type);

		Page<BusiProductPropertyDto> page = (Page<BusiProductPropertyDto>) items;

		pageData.setRows(items);
		pageData.setTotal(page.getTotal());
	
		return pageData;
	}

	@Override
	@Transactional
	public void save(BusiProductPropertyDto productProperty) {
		productProperty.setId(productProperty.getIdentity());
		busiProductPropertyDtoMapper.insert(productProperty);
	}

	@Override
	@Transactional
	public void updateSelective(BusiProductPropertyDto productProperty) {
		busiProductPropertyDtoMapper
				.updateByPrimaryKeySelective(productProperty);
	}

	@Override
	public List<BusiProductPropertyDto> listByTypes(
			List<EProductProperty> typeList) {
		return busiProductPropertyDtoMapper.listByTypes(typeList);
	}

	@Override
	public BusiProductPropertyDto findById(String id) {
		return busiProductPropertyDtoMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void delete(String id) {
		busiProductPropertyDtoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int queryRef(String id) {
		return busiProductPropertyDtoMapper.queryByRef(id);
	}
}
