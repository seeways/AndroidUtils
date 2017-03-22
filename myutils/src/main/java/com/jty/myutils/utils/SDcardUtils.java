package com.jty.myutils.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/** 
 * SD卡辅助类
 * @author TaoYuan
 */

public class SDcardUtils {
	/** 
     * 判断SDCard是否可用 
     *  
     * @return 
     */
	public static boolean isSDCardEnable(){
		return Environment.getExternalStorageState()
				.equals(Environment.MEDIA_MOUNTED);
		
	}
	/** 
     * 获取SD卡路径 
     *  
     * @return 
     */ 
	public static String getSDCardPath(){
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+File.separator;
	}
	/** 
     * 获取SD卡的剩余容量 单位byte 
     *  
     * @return 
     */

	@SuppressWarnings("deprecation")
	public static long getSDCardsize(){
		if(isSDCardEnable()){
			
            StatFs stat=new StatFs(getSDCardPath());
            //获取空闲数据块的数量
            long availableBlocks = (long)stat.getAvailableBlocks();
            //获取单个数据块的大小(byte)
            long oneBlock = stat.getBlockSize();
            
            return oneBlock * availableBlocks;
		}
		
		return 0;
	}
	
	
	/** 
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte 
     *  
     * @param filePath 
     * @return 容量字节 SDCard可用空间，内部存储可用空间 
     */ 
	@SuppressWarnings({ "unused", "deprecation" })
	public static long getavailable (String filePath){
		/*
		 * // 如果是sd卡的下的路径，则获取sd卡可用容量  
        if (filePath.startsWith(getSDCardPath()))  
        {  
            filePath = getSDCardPath();  
        } else  
        {// 如果是内部存储的路径，则获取内存存储的可用容量  
            filePath = Environment.getDataDirectory().getAbsolutePath();  
        }  
        StatFs stat = new StatFs(filePath);  
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;  
        return_back stat.getBlockSize() * availableBlocks;
		 */
		
		// 如果是sd卡的下的路径，则获取sd卡可用容量  
		if(filePath.startsWith(getSDCardPath())){
			filePath = getSDCardPath();
		}else{
		// 如果是内部存储的路径，则获取内存存储的可用容量  
		filePath = getDataDirectoryPath();
		}
		
		StatFs stat=new StatFs(filePath);
		long availableBlock = stat.getAvailableBlocks();
		
		return availableBlock * stat.getBlockSize();
		
	}
	
	
	 /** 
     * 获取系统存储路径 
     *  
     * @return 
     */
	public static String getRootDirectoryPath(){
		return Environment.getRootDirectory().getAbsolutePath();
	}
	/**
	 * 获取内部存储路径
	 */
	public static String getDataDirectoryPath(){
		return Environment.getDataDirectory().getAbsolutePath();
	}

	
	
}
