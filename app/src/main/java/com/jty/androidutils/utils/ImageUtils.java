package com.jty.androidutils.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  Created by JTY on 2016/10/9 0009.
 *  ImageUtils
 */

public class ImageUtils {
    /**
     * 根据路径得到原图的宽高
     * @param path 图片路径
     * @return
     */
    public static int[] getImgWidthAndHeight(String path){

        //获取Options对象
        BitmapFactory.Options options = new BitmapFactory.Options();
        //仅做解码处理，不加载到内存
        options.inJustDecodeBounds = true;
        //解析文件
        BitmapFactory.decodeFile(path, options);
        //获取宽高
        int imgWidth = options.outWidth;
        int imgHeight = options.outHeight;
        int[] wh = {imgWidth,imgHeight};
        return wh;
    }





    /**
     * 功能：将签好名的bitmap保存到sd卡
     * @param bitmap
     */
    public static void saveImg(String savePath,Bitmap bitmap,String imgName) {
        File file = new File(savePath);//要保存的文件地址和文件名
        //没有文件的话创建一个
        if (!file.exists()) {
            file.mkdir();
        }


        //签名图片"hand_sign_" + TimeUtils.getTimeStemp() + ".png"
        File imageFile = new File(file,imgName);
        try {
            if(!imageFile.exists()){
                imageFile.createNewFile();
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
