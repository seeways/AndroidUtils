package com.jty.myutils.utils;

import android.os.Environment;

import java.io.File;

/**
 * SD卡辅助类
 *
 * @author TaoYuan
 */

public class SDcardUtils {

    /**
     * 判断外存是否可用
     *
     * @return true or false
     */
    public static boolean isExternalStorageEnable() {
        return Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 获取外存路径
     *
     * @return 外存路径
     */
    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 获取内部存储路径
     */
    public static String getDataDirectoryPath() {
        return Environment.getDataDirectory().getAbsolutePath();
    }


}
