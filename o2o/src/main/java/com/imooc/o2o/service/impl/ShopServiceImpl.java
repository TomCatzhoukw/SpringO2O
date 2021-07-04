package com.imooc.o2o.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) {
		//空值判断
		if(shop==null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		
		try {
			//给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			
			//添加店铺信息
			int effectedNum= shopDao.insertShop(shop);
			
			if(effectedNum <=0) {
				throw new ShopOperationException("店铺创建失败");
			}else {
				if(shopImgInputStream!=null) {
					try {
						addShopImg(shop,shopImgInputStream,fileName);
						// shop.getShopImg();
					} catch (Exception e) {
						throw new ShopOperationException("addImg error:"+ e.getMessage());
					}
					//更新图片地址
					effectedNum=shopDao.updateShop(shop);
					if(effectedNum<=0) {
						
						throw new ShopOperationException("更新图片地址失败");
					}
					
				}
			}
			
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:"+e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, InputStream shopImage,String fileName) {
		String dest=PathUtil.getShopImagePath(shop.getShopId());
		String shopImhAddr =ImageUtil.generateThumbnails(shopImage,fileName, dest);
		shop.setShopImg(shopImhAddr);
		
	}

}
