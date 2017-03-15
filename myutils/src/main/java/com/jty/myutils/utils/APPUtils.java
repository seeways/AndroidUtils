package com.jty.myutils.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

/** 
 * APP辅助类
 * @author jty 
 */

public class APPUtils {

	/**
	 * 获取应用程序名称
	 * @param context
	 * @return
     */
	public static String getAppName(Context context){
		try{
			int labelRes = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0)
					.applicationInfo
					.labelRes;

			return context.getResources().getString(labelRes); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 获取版本号
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context){
		try{
			PackageInfo packageInfo=context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			
			return packageInfo.versionName;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获得版本码
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context){
		try{
			return context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0)
					.versionCode;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0 ;
	}

}
