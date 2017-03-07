package com.jty.androidutils.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by JTY on 2016/10/25 0025.
 */

public class DialogUtils{


    public static void CommonDialog(Context context ,String title,String msg, DialogInterface.OnClickListener listener){
        // 创建退出对话框
        AlertDialog isExit = new AlertDialog.Builder(context).create();
        // 设置对话框标题
        isExit.setTitle(title);
        // 设置对话框消息
        isExit.setMessage(msg);
        // 添加选择按钮并注册监听
        isExit.setButton(DialogInterface.BUTTON_POSITIVE, "确定", listener);
        isExit.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", listener);
        // 显示对话框
        isExit.show();
    }

    public static void HintDialog(Context context ,String msg, DialogInterface.OnClickListener listener){
        // 创建退出对话框
        AlertDialog isExit = new AlertDialog.Builder(context).create();
        // 设置对话框标题
        isExit.setTitle("温馨提示");
        // 设置对话框消息
        isExit.setMessage(msg);
        // 添加选择按钮并注册监听
        isExit.setButton(DialogInterface.BUTTON_POSITIVE, "确定", listener);
        // 显示对话框
        isExit.show();
    }




}
