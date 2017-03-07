package com.jty.androidutils.utils;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  Created by JTY on 2016/10/9 0009.
 *  ImageUtils
 */

public class ImageUtils {

    /**
     * 功能：将签好名的bitmap保存到sd卡
     * @param bitmap
     */
    public static void saveImg(String savePath,Bitmap bitmap,String imgName) {
        //"/sdcard/IYIN"
        File file = new File(savePath);//要保存的文件地址和文件名
        //没有文件的话创建一个
        if (!file.exists()) {
            file.mkdir();
        }


        //签名图片"hand_sign_" + TimeUtils.getTimeStemp() + ".png"
        File imageFile = new File(file,imgName);
        L.e("file:"+file);
        try {
            if(!imageFile.exists()){
                boolean flag = imageFile.createNewFile();
                L.e("flag:"+flag);
            }


            FileOutputStream fos = new FileOutputStream(imageFile);
//            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }









}
