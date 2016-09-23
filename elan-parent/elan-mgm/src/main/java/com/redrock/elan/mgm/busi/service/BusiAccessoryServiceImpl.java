package com.redrock.elan.mgm.busi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ylinkpay.framework.core.model.PageData;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.redrock.elan.common.project.mgm.dto.busi.BusiAccessoryDto;
import com.redrock.elan.mgm.busi.dao.BusiAccessoryDtoMapper;

@Service("busiAccessoryService")
public class BusiAccessoryServiceImpl implements BusiAccessoryService {

	@Autowired
	BusiAccessoryDtoMapper accessoryDtoMapper;
	
	@Override
	public void save(BusiAccessoryDto accessoryDto) {
		accessoryDto.setId(accessoryDto.getIdentity());
		accessoryDto.setCreateTime(new Date());
		accessoryDtoMapper.insert(accessoryDto);
	}

	@Override
	public void updateSelective(BusiAccessoryDto accessoryDto) {
		accessoryDtoMapper.updateByPrimaryKeySelective(accessoryDto);
	}

	@Override
	public PageData<BusiAccessoryDto> listPage(
			PageData<BusiAccessoryDto> pageData, BusiAccessoryDto queryParam) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<BusiAccessoryDto> items = accessoryDtoMapper.list(queryParam);
		
		Page<BusiAccessoryDto> page = (Page<BusiAccessoryDto>) items;
		
		pageData.setRows(items);
		pageData.setTotal(page.getTotal());
		
		return pageData;
	}

	@Override
	public BusiAccessoryDto findById(String id) {
		return accessoryDtoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(String id) {
		accessoryDtoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BusiAccessoryDto> findByIds(List<String> ids) {
		return accessoryDtoMapper.findByIds(ids);
	}

}
