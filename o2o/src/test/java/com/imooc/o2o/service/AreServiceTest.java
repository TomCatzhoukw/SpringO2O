package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;

public class AreServiceTest extends BaseTest {
	
	@Autowired
	private AreaService areService;
	
	
	@Test
	public void testGetAreaList() {
		
		List<Area> areaList=areService.getAreList();
		assertEquals("重庆", areaList.get(0).getAreaName());
		
	}
	

}
