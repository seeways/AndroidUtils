package com.jty.myutils.utils.kt

import android.app.Activity
import android.content.Context
import android.widget.Toast

/**
 * created by Taoyuan on 2021/6/14
 * Toast管理类
 */
object T {

    /**
     * Toast控制，在application的onCreate函数里面初始化
     */
    var isShow = true

    fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
        if (isShow) Toast.makeText(context, this, duration).show()
    }


    fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
        if (isShow) Toast.makeText(context, this, duration).show()
    }

    infix fun String.showToastOnUI(activity: Activity) {
        if (isShow) activity.runOnUiThread { this.showToast(activity) }
    }


}