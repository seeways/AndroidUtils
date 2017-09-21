package com.jty.mykotlinutils.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * Get Screen Info
 *
 * This Class Cannot be Instantiated,
 * Direct Use Static Method Call It In Java.
 * Just Like This : ScreenUtils.Companion.getScreenHeight(context);
 */

class ScreenUtils private constructor() {

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        /**
         * Get Screen Height
         *
         * @param context
         * @return int
         */
        fun getScreenHeight(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.heightPixels

        }

        /**
         * Get Screen Width
         *
         * @param context
         * @return int
         */
        fun getScreenWidth(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.widthPixels
        }

        /**
         * Get Status Height
         *
         * @param context
         * @return int
         */
        fun getStatusHeight(context: Context): Int {
            var statusHeight = -1

            try {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val `object` = clazz.newInstance()
                val height = Integer.parseInt(clazz.getField("status_bar_height")
                        .get(`object`).toString())
                statusHeight = context.resources.getDimensionPixelSize(height)
            } catch (e: Exception) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

            return statusHeight
        }

        /**
         * Shot With Screen Include StatusBar
         *
         * @param activity
         * @return bitmap
         */
        fun snapShotWithStatusBar(activity: Activity): Bitmap {
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val bitmap = view.drawingCache
            val width = getScreenWidth(activity)
            val height = getScreenHeight(activity)
            val bmp = Bitmap.createBitmap(bitmap, 0, 0, width, height)
            view.destroyDrawingCache()
            return bmp

        }

        /**
         * Shot With Screen Exclude StatusBar
         *
         * @param activity
         * @return bitmap
         */
        fun snapShotWithoutStatusBar(activity: Activity): Bitmap {
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val bitmap = view.drawingCache
            val frame = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(frame)
            val statusBarHeight = frame.top

            val height = getScreenHeight(activity)
            val width = getScreenWidth(activity)
            val bp = Bitmap.createBitmap(bitmap,
                    0, statusBarHeight, width, height - statusBarHeight)
            view.destroyDrawingCache()
            return bp
        }
    }
}
