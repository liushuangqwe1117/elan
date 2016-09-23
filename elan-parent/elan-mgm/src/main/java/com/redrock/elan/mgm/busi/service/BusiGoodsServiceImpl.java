package com.redrock.elan.mgm.busi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ylinkpay.framework.core.model.PageData;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.redrock.elan.common.project.mgm.dto.busi.BusiGoodsDto;
import com.redrock.elan.mgm.busi.dao.BusiGoodsDtoMapper;

@Service("busiGoodsService")
public class BusiGoodsServiceImpl implements BusiGoodsService {
	
	@Autowired
	BusiGoodsDtoMapper busiGoodsDtoMapper;

	@Override
	public void save(BusiGoodsDto accessoryDto) {
		accessoryDto.setId(accessoryDto.getIdentity());
		accessoryDto.setCreateTime(new Date());
		busiGoodsDtoMapper.insert(accessoryDto);
	}

	@Override
	public void updateSelective(BusiGoodsDto accessoryDto) {
		busiGoodsDtoMapper.updateByPrimaryKeySelective(accessoryDto);
	}

	@Override
	public PageData<BusiGoodsDto> listPage(PageData<BusiGoodsDto> pageData,
			BusiGoodsDto queryParam) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<BusiGoodsDto> items = busiGoodsDtoMapper.list(queryParam);
		
		Page<BusiGoodsDto> page = (Page<BusiGoodsDto>) items;
		
		pageData.setRows(items);
		pageData.setTotal(page.getTotal());
		
		return pageData;
	}

	@Override
	public BusiGoodsDto findById(String id) {
		return busiGoodsDtoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(String id) {
		busiGoodsDtoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BusiGoodsDto> findByIds(List<String> ids) {
		return busiGoodsDtoMapper.findByIds(ids);
	}

}
