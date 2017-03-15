package com.jty.myutils.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Snackbar 管理类
 * Created by JTY on 2016/9/19 0019.
 */
public class S {

    public static void showNormalSnack(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public static void showNormalSnack(View view, int text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }


    public static void showActionSnack(View view, CharSequence text, CharSequence action, View.OnClickListener listener) {
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setAction(action, listener).show();
    }

    public static void showActionSnack(View view, int text, CharSequence action, View.OnClickListener listener) {
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setAction(action, listener).show();
    }

}
