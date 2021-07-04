package com.imooc.o2o.util;

public class PathUtil {
	
	private static String os=System.getProperty("os.name");
	
	/**
	 * 获取项目图片存放路径
	 * @return
	 */
	public static String getImageBasePath(){
		String basePath="";
		String seperator=System.getProperty("file.separator");
		if(os.toLowerCase().startsWith("win")){
			basePath="D:/ZKW/workspace/projectDev/image/";
		}else {
			basePath="/home/zkw/workspace/projectDev/image/";
		}
		basePath.replace("/", seperator);
		return basePath;
	}
	
	
	/**
	 * 店铺图片上传路径
	 * @param shopId
	 * @return
	 */
	public static String getShopImagePath(long shopId) {
		String imagePath="";
		String seperator=System.getProperty("file.separator");
		if(os.toLowerCase().startsWith("win")){
			imagePath="D:/ZKW/image/";
		}else {
			imagePath="/upload/item/shop/"+shopId+"/";
		}
		imagePath.replace("/", seperator);
		return imagePath;
		
	}
	
	public static void main(String[] args ) {
		System.out.println( System.getProperty("file.separator"));
		}

}
