package com.imooc.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaService;




@Controller
@RequestMapping("/superadmin")
public class AreaController {
	Logger logger=LoggerFactory.getLogger(AreaController.class);
	
	@Autowired
	private AreaService areService;
	// /superadminlistarea/
	@RequestMapping(value="/listarea",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listArea(){
		logger.info("===start===");
		long startTime=System.currentTimeMillis();
		Map<String, Object> modelMap=new HashMap<String, Object>();
		List<Area> list = new ArrayList<Area>();
		list = areService.getAreList();
		try {
			modelMap.put("rows", list);
			modelMap.put("total", list.size());
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMessage", e.getMessage());
		}
		logger.error("testError");
		long endTime=System.currentTimeMillis();
		logger.debug("costTime:[{}ms]",endTime-startTime);
		logger.info("===end===");
		return modelMap;
	}

}
