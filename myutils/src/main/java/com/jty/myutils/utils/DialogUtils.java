package com.jty.myutils.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.jty.myutils.R;


/**
 * Created by JTY on 2016/10/25 0025.
 */

/**
 * DialogUtils
 */
public class DialogUtils{


    public static void CommonDialog(Context context , String msg, DialogInterface.OnClickListener listener){
        CommonDialog(context,"提示",msg,listener);
    }

    public static void CommonDialog(Context context , String title, String msg, DialogInterface.OnClickListener listener){
        CommonDialog(context,"提示",msg,"确定","取消",listener);
    }

    public static void CommonDialog(Context context , String title, String msg,CharSequence confirmButtonText,CharSequence cancelButtonText, DialogInterface.OnClickListener listener){
        // 创建退出对话框
        AlertDialog isExit = new AlertDialog.Builder(context).create();
        // 设置对话框标题
        isExit.setTitle(title);
        // 设置对话框消息
        isExit.setMessage(msg);
        // 添加选择按钮并注册监听
        isExit.setButton(DialogInterface.BUTTON_POSITIVE, confirmButtonText, listener);
        isExit.setButton(DialogInterface.BUTTON_NEGATIVE, cancelButtonText, listener);
        // 显示对话框
        isExit.show();
    }

    public static void CommonDialog(Context context , String title, View view, CharSequence confirmButtonText, CharSequence cancelButtonText, DialogInterface.OnClickListener listener){
        // 创建退出对话框
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        // 设置对话框标题
        dialog.setTitle(title);
        // 设置view
        dialog.setView(view);
        // 添加选择按钮并注册监听
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, confirmButtonText, listener);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, cancelButtonText, listener);
        // 显示对话框
        dialog.show();
    }

    public static void HintDialog(Context context ,String msg, DialogInterface.OnClickListener listener){
        HintDialog(context,"提示",msg,listener);
    }

    public static void HintDialog(Context context ,String title,String msg, DialogInterface.OnClickListener listener){
        HintDialog(context,title,msg,"确定",listener);
    }

    public static void HintDialog(Context context ,String title,String msg, CharSequence buttonText, DialogInterface.OnClickListener listener){
        // 创建退出对话框
        AlertDialog isExit = new AlertDialog.Builder(context).create();
        // 设置对话框标题
        isExit.setTitle(title);
        // 设置对话框消息
        isExit.setMessage(msg);
        // 添加选择按钮并注册监听
        isExit.setButton(DialogInterface.BUTTON_POSITIVE, buttonText, listener);
        // 显示对话框
        isExit.show();
    }



}
