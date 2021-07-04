package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;

public class AreaDaoTest extends BaseTest{
	
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea() {
		System.out.println("进来了");
		List<Area> areaList =areaDao.queryArea();
		System.out.println("输出");
		assertEquals(4, areaList.size());
	}

}
