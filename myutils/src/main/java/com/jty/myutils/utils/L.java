package com.jty.myutils.utils;

import android.util.Log;

/**
 * Log管理类
 *
 * @author jty
 */
public class L {

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "way";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }



    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }



    public static void showLarge(String msg) {
        showLarge(msg, 4000, TAG);
    }

    public static void showLarge(String msg, int showLength) {
        showLarge(msg, showLength, TAG);
    }

    public static void showLarge(String msg, int showLength, String tag) {
        if (msg.length() > showLength) {

            for(int i=0;i<msg.length();i+=showLength){
                if(i+showLength<msg.length())
                    e(tag, msg.substring(i, i+showLength));
                else
                    e(tag, msg.substring(i, msg.length()));
            }

        } else {
            e(tag, msg);
        }

    }
}