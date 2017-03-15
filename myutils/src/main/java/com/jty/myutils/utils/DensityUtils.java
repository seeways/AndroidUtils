package com.jty.myutils.utils;

import android.content.Context;
import android.util.TypedValue;

/** 
 * 常用单位转换辅助类
 * @author jty 
 */

public class DensityUtils {
	
	private DensityUtils(){
		//cannot be instantiated
		throw new UnsupportedOperationException("cannot be instantiated");
	}
	
	/** 
     * dp转px 
     *  
     * @param context 
     * @param dpval
     * @return 
     */  
	public static int dp2px(Context context,float dpval){
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
				dpval, context.getResources().getDisplayMetrics());
		
	}
	/** 
     * sp转px 
     *  
     * @param context 
     * @param spval
     * @return 
     */ 
	public static int sp2px(Context context,float spval){
		return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spval, context.getResources().getDisplayMetrics());
	}
	/** 
     * px转dp 
     *  
     * @param context 
     * @param dpval
     * @return 
     */ 
	public static float px2dp(Context context,float dpval){
		final float scale=context.getResources().getDisplayMetrics().density;
		return (dpval/scale);
	}
	/** 
     * px转sp 
     *  
     * @param context
     * @param pxval
	 * @return 
     * @return 
     */ 
	public static float floatpx2sp(Context context,float pxval){
		return (pxval/context.getResources().getDisplayMetrics().scaledDensity);
	}
	
	
	
	
}
