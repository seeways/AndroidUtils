package com.jty.myutils.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/** 
 * 网络工具类
 * @author jty 
 */

public class NetUtils {
	/** 
     * 判断网络是否连接 
     *  
     * @param context 
     * @return 
     */ 
	public static boolean isConnected(Context context){
		ConnectivityManager connect=(ConnectivityManager) context.
				getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connect!=null){
			@SuppressLint("MissingPermission") NetworkInfo networkInfo=connect.getActiveNetworkInfo();
			if(networkInfo!=null && networkInfo.isConnected()){
				if(networkInfo.getState() == NetworkInfo.State.CONNECTED){
					return true;
				}
			}
		}
		return false;
	}
	
	/** 
     * 判断是否是wifi连接 
     */  
	@SuppressLint("MissingPermission")
	public static boolean isWifi(Context context){
		ConnectivityManager connect=(ConnectivityManager) context.
				getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connect==null){
			return false;
		}
		return connect.getActiveNetworkInfo().getType()==ConnectivityManager.TYPE_WIFI;
	}

	/**
	 * 获取Ip地址
	 * @return net address
	 */
	public static String getIP(){

		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address))
					{
						return inetAddress.getHostAddress();
					}
				}
			}
		}
		catch (SocketException ex){
			ex.printStackTrace();
		}
		return null;
	}

	 /** 
     * 打开网络设置界面 
     */  
	public static void openSet(Activity activity){
		Intent intent=new Intent("/");
		ComponentName componentName=new ComponentName("com.android.settings",  
                "com.android.settings.WirelessSettings");
		intent.setComponent(componentName);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivityForResult(intent, 0);
	}
	
	
	
}
