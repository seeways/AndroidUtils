package com.jty.mykotlinutils.utils

import android.content.Context
import android.content.pm.PackageInfo

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * Get APP Basic Args
 */

object APPUtils {

    /**
     * Get APP Name
     * @param context
     * @return string
     */
    fun getAppName(context: Context): String? {
        try {
            val labelRes = context.packageManager
                    .getPackageInfo(context.packageName, 0)
                    .applicationInfo
                    .labelRes

            return context.resources.getString(labelRes)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }


    /**
     * Get APP Version Name
     * @param context
     * @return string
     */
    fun getVersionName(context: Context): String? {
        try {
            val packageInfo = context.packageManager
                    .getPackageInfo(context.packageName, 0)

            return packageInfo.versionName

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * Get APP Version Code
     * @param context
     * @return int
     */
    fun getVersionCode(context: Context): Int {
        try {
            return context.packageManager
                    .getPackageInfo(context.packageName, 0)
                    .versionCode
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0
    }
}
