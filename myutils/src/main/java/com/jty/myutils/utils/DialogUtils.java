package com.jty.myutils.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.jty.myutils.R;


/**
 * Created by JTY on 2016/10/25 0025.
 */

/**
 * DialogUtils
 */
public class DialogUtils{


    public static void CommonDialog(Context context ,String title,String msg, DialogInterface.OnClickListener listener){
        // 创建退出对话框
        AlertDialog isExit = new AlertDialog.Builder(context).create();
        // 设置对话框标题
        if(title==null){
            isExit.setTitle(R.string.dialog_title_tip);
        }else{
            isExit.setTitle(title);
        }
        // 设置对话框消息
        isExit.setMessage(msg);
        // 添加选择按钮并注册监听
        isExit.setButton(DialogInterface.BUTTON_POSITIVE, context.getResources().getString(R.string.confirm), listener);
        isExit.setButton(DialogInterface.BUTTON_NEGATIVE, context.getResources().getString(R.string.cancel), listener);
        // 显示对话框
        isExit.show();
    }

    public static void HintDialog(Context context ,String title,String msg, DialogInterface.OnClickListener listener){
        // 创建退出对话框
        AlertDialog isExit = new AlertDialog.Builder(context).create();
        // 设置对话框标题
        if(title==null){
            isExit.setTitle(R.string.dialog_title_tip);
        }else{
            isExit.setTitle(title);
        }

        // 设置对话框消息
        isExit.setMessage(msg);
        // 添加选择按钮并注册监听
        isExit.setButton(DialogInterface.BUTTON_POSITIVE, context.getResources().getString(R.string.confirm), listener);
        // 显示对话框
        isExit.show();
    }




}
