package com.jty.myutils.utils;

import android.content.Context;

/**
 * Created by JTY on 2017/3/15 0015
 */

public class Utils {
        private static Context context;
        //初始化
        public static void init(Context context) {
            Utils.context = context.getApplicationContext();
        }
        //获取ApplicationContext
        public static Context getContext() {
            if (context != null) return context;
            throw new NullPointerException("please init first");
        }

}
