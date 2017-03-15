package com.jty.myutils.utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by JTY on 2016/9/24 0024.
 * 转换帮助类
 */

public class ConvertUtils {
    /**
     * BitMap to Bytes
     *
     * @param bm
     * @return
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * String to int
     * @param string
     * @return
     */
    public static int String2Int(String string){
        return Integer.parseInt(string);
    }


}
