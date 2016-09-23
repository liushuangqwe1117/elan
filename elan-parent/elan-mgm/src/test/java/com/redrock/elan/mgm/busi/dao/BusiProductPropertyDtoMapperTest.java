package com.redrock.elan.mgm.busi.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BusiProductPropertyDtoMapperTest extends BaseTest {
	
	@Autowired
	BusiProductPropertyDtoMapper productPropertyDtoMapper;

	@Test
	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertSelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testListByType() {
		fail("Not yet implemented");
	}

	@Test
	public void testListByTypes() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryByRef() {
		Integer count = productPropertyDtoMapper.queryByRef("aaaaaa");
		System.out.println("count:" + count);
	}

}
