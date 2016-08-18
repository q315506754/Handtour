package com.handtours.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * @author Cloudbian
 * @version createTime：2012-7-9 下午9:48:12
 */
public class UpYunUtil {
	private final static String USERNAME = "webmaster";
	private final static String USERPWD = "Zhangxing_321";
	private final static String SPACENAME = "handtours";
    public final static String URL = "http://handtours.b0.upaiyun.com/";
//    public final static String URL = "http://image.handtours.com/";

	private static UpYun upyun = null;

	/**
	 * init upyun
	 * */
	private static void initUpYun(){
		if(null==upyun){
			upyun = new UpYun(SPACENAME,USERNAME,USERPWD);
		}
	}

	/**
	 * 上传图片，流传输，不自动建立目录
	 * @param filePath(云文件路径，需要加"/") file保存的文件
	 * @return boolean is success
	 * @throws Exception 保存异常
	 * */
	@Deprecated
	private static boolean saveFileToCloud(String filePath, File file) throws Exception {
		initUpYun();
		return upyun.writeFile(filePath, file);
	}

	/**
	 * 上传图片，流传输，不自动建立目录
	 * @param filePath(云文件路径，需要加"/") file保存的文件
	 * @return boolean is success
	 * @throws Exception 保存异常
	 * */
    @Deprecated
    private static boolean saveFileToCloud(String filePath, FileInputStream fileStream) throws Exception {
		initUpYun();
		return upyun.writeFile(filePath, fileStream,false);
	}

	/**
	 * 上传图片返回文件名
	 * @param filePath(云文件路径，不要加"/") file保存的文件
	 * @return String fileName
	 * @throws Exception 保存异常
	 * */
	public static String uploadFileAndGetFileName(String filePath, File file) throws Exception {
		initUpYun();
		String newFilePath = UpYun.md5(filePath);
		if(upyun.writeFile("/"+newFilePath, file)){
			return newFilePath;
		}
		return null;
	}

	public static String uploadFileAndGetFileName(File file) throws Exception {
		initUpYun();
        String name = file.getName();
        String str = name + System.currentTimeMillis();
        String newFilePath = UpYun.md5(str);
		if(upyun.writeFile("/"+newFilePath, file)){
			return newFilePath;
		}
		return null;
	}

	/**
	 * 上传图片
     * e.g.
     *
     * a/b/c/ + 20151210102927.png -> domain/a/b/c
     *
	 * @param filePath(云文件路径，不要加"/") file保存的文件
	 * @return String fileName
	 * @throws Exception 保存异常
	 * */
	public static boolean uploadFile(String filePath, File file) throws Exception {
		initUpYun();
		return upyun.writeFile("/"+filePath, file);
	}

	/**
	 * 删除图片
	 * @param filePath(云文件路径)
	 * @throws Exception 删除异常
	 * */
	public static boolean deleteFileFromCloud(String filePath) throws Exception {
		initUpYun();
		return upyun.deleteFile(filePath);
	}

	/**
	 * 获取图片信息
	 * @param filePath(云文件路径)
	 * @return null 如果filePath对应的文件不存在,否则返回这些数据{date=1471334766, size=609098, type=file}
	 * @throws Exception
	 * */
	public static Map getFileMetaInfo(String filePath) throws Exception {
		initUpYun();
		return upyun.getFileInfo(filePath);
	}

	/**
	 * 获取文件
	 * @param filePath(需加"/"),需要存储的File对象
	 * @throws Exception
	 * */
	public static boolean getFile(String filePath, File file) throws Exception {
		initUpYun();
		return upyun.readFile(filePath,file);
	}
}
