package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	
	private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	
	private static final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static Random r = new Random();
	
	private static Logger logger=LoggerFactory.getLogger(ImageUtil.class);
	
	/**
	 * 将 CommonsMultipartFile 转换成 File类
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
		File newFile= new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
		
	}
	
	public static String generateThumbnails(InputStream thumbnailInputStream,String fileName,String targetAddr){
		
		//文件随机取名字
		String realFilleName = getRandomFileName();
		//取文件名后缀
		String extension = getFileExtension(fileName);
		//创建文件夹
		
		makeDirPath(targetAddr);
		
		String relativeAddr = targetAddr + realFilleName + extension;
		logger.debug("current relativeAddr is:"+relativeAddr);
		String os=System.getProperty("os.name");
		File dest=null;
		if(os.toLowerCase().startsWith("win")){
			dest=new File(relativeAddr);
		}else {
			dest=new File(PathUtil.getImageBasePath()+relativeAddr);
		}
		
		
		logger.debug("current complete addr is:"+PathUtil.getImageBasePath()+relativeAddr);
		try {
			Thumbnails.of(thumbnailInputStream)
			.size(300, 300)
			.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "watermark.jpg")),0.25f)
			.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return relativeAddr;
		
	}
	
	/**
	 * 新建目标路径涉及的目录
	 * @param targetAddr 路径
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImageBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
	}

	/**
	 * 获取文件流后缀名
	 * @param thumbnail 
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 文件随机取名字  当前年月日小时分钟秒 + 五位随机数
	 * @return
	 */
	public static String getRandomFileName() {
		// 随机五位数
		int rannum=r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormate.format(new Date());
		return rannum + nowTimeStr;
	}

	public static void main(String[] args) throws IOException {
		 
		Thumbnails.of(new File("D:\\ZKW\\workspace\\o2o\\src\\main\\resources\\watermark.jpg"))
		.size(300, 300)
		.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "watermark.jpg")),0.25f)
		.outputQuality(0.8f).toFile("D:\\ZKW\\workspace\\o2o\\src\\main\\\\resources\\小黄人.jpg");
	}
	
	

}
