package com.jty.mykotlinutils.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * CommonDialog And HintDialog
 */
object DialogUtils {


    fun CommonDialog(context: Context, msg: String, listener: DialogInterface.OnClickListener) {
        CommonDialog(context, "提示", msg, listener)
    }

    fun CommonDialog(context: Context, title: String, msg: String, listener: DialogInterface.OnClickListener) {
        CommonDialog(context, "提示", msg, "确定", "取消", listener)
    }

    fun CommonDialog(context: Context, title: String, msg: String, confirmButtonText: CharSequence, cancelButtonText: CharSequence, listener: DialogInterface.OnClickListener) {
        // 创建退出对话框
        val isExit = AlertDialog.Builder(context).create()
        // 设置对话框标题
        isExit.setTitle(title)
        // 设置对话框消息
        isExit.setMessage(msg)
        // 添加选择按钮并注册监听
        isExit.setButton(DialogInterface.BUTTON_POSITIVE, confirmButtonText, listener)
        isExit.setButton(DialogInterface.BUTTON_NEGATIVE, cancelButtonText, listener)
        // 显示对话框
        isExit.show()
    }

    fun HintDialog(context: Context, msg: String, listener: DialogInterface.OnClickListener) {
        HintDialog(context, "提示", msg, listener)
    }

    fun HintDialog(context: Context, title: String, msg: String, listener: DialogInterface.OnClickListener) {
        HintDialog(context, title, msg, "确定", listener)
    }

    fun HintDialog(context: Context, title: String, msg: String, buttonText: CharSequence, listener: DialogInterface.OnClickListener) {
        // 创建退出对话框
        val isExit = AlertDialog.Builder(context).create()
        // 设置对话框标题
        isExit.setTitle(title)
        // 设置对话框消息
        isExit.setMessage(msg)
        // 添加选择按钮并注册监听
        isExit.setButton(DialogInterface.BUTTON_POSITIVE, buttonText, listener)
        // 显示对话框
        isExit.show()
    }

}

