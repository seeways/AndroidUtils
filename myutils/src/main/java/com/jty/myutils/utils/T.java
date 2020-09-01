package com.jty.myutils.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by JTY on 2016/8/11 0011.
 * Toast管理类
 */
public class T {

    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;


    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    @Deprecated
    public static void showShort(Context context, CharSequence message) {
        if (isShow) show(context, message, false);
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    @Deprecated
    public static void showShort(Context context, int message) {
        if (isShow) show(context, message, false);
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    @Deprecated
    public static void showLong(Context context, CharSequence message) {
        if (isShow) show(context, message, true);
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    @Deprecated
    public static void showLong(Context context, int message) {
        if (isShow) show(context, message, true);
    }

    private static Toast toast = null;

    public static void show(Context context, CharSequence message) {
        show(context, message, false);
    }

    public static void show(Context context, CharSequence message, boolean isLong) {
        if (toast != null) {
            toast.setText(message);
        } else {
            if (isLong) toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            else toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void show(Context context, int message) {
        show(context, message, false);
    }

    public static void show(Context context, int message, boolean isLong) {
        if (toast != null) {
            toast.setText(message);
        } else {
            if (isLong) toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            else toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showToastOnUI(Activity activity, String msg) {
        activity.runOnUiThread(() -> T.show(activity, msg, false));
    }


}
