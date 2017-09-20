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
     * @param dp
     * @return px
     */  
	public static int dp2px(Context context,float dp){
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
		
	}
	/** 
     * sp转px 
     *  
     * @param context 
     * @param sp
     * @return px
     */ 
	public static int sp2px(Context context,float sp){
		return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.getResources().getDisplayMetrics());
	}
	/** 
     * px转dp 
     *  
     * @param context 
     * @param px
     * @return dp
     */ 
	public static float px2dp(Context context,float px){
		final float scale=context.getResources().getDisplayMetrics().density;
		return (px/scale);
	}
	/** 
     * px转sp 
     *  
     * @param context
     * @param px
	 * @return sp
     */
//	@Deprecated
	public static float px2sp(Context context,float px){
		return (px/context.getResources().getDisplayMetrics().scaledDensity);
	}
	
	
	
	
}
