package com.redrock.elan.mgm.busi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ylinkpay.framework.core.model.PageData;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.redrock.elan.common.project.mgm.dto.busi.BusiPearlDto;
import com.redrock.elan.mgm.busi.dao.BusiPearlDtoMapper;

@Service("busiPearlService")
public class BusiPearlServiceImpl implements BusiPearlService {
	
	@Autowired
	BusiPearlDtoMapper busiPearlDtoMapper;
	
	@Override
	@Transactional
	public void save(BusiPearlDto busiPearlDto) {
		busiPearlDto.setId(busiPearlDto.getIdentity());
		busiPearlDto.setCreateTime(new Date());
		busiPearlDtoMapper.insert(busiPearlDto);
	}

	@Override
	@Transactional
	public void updateSelective(BusiPearlDto busiPearlDto) {
		busiPearlDtoMapper.updateByPrimaryKeySelective(busiPearlDto);
	}

	@Override
	public PageData<BusiPearlDto> listPage(PageData<BusiPearlDto> pageData,
			BusiPearlDto queryParam) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<BusiPearlDto> items = busiPearlDtoMapper.list(queryParam);
		
		Page<BusiPearlDto> page = (Page<BusiPearlDto>) items;
		
		pageData.setRows(items);
		pageData.setTotal(page.getTotal());
		
		return pageData;
	}

	@Override
	public BusiPearlDto findById(String id) {
		return busiPearlDtoMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void delete(String id) {
		busiPearlDtoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BusiPearlDto> findByIds(List<String> ids) {
		return busiPearlDtoMapper.findByIds(ids);
	}

}
