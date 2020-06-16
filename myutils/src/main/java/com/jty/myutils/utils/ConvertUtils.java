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
     * @param bm bitmap
     * @return bytes[]
     */
    public static byte[] BitmapToBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * String to int
     *
     * @param string 字符串
     * @return 整数
     */
    public static int StringToInt(String string) {
        return Integer.parseInt(string);
    }


    /**
     * str2HexStr
     *
     * @param str normal string
     * @return hex string
     */
    public static String strToHexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = b & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    /**
     * hexStringToByte
     *
     * @param hex hexString
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 数组转换成十六进制字符串
     *
     * @param bArray byteArray
     * @return HexString
     */
    public static String bytesToHexString(byte[] bArray) {
        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
        for (byte b : bArray) {
            sTemp = Integer.toHexString(0xFF & b);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 检测是否float，否返回0
     *
     * @param value float字符串
     * @return float字符串
     */
    public static String nulltoFloatDefalt(String value) {
        if (!isFloatValue(value)) value = "0";
        return value;
    }

    private static boolean isFloatValue(String val) {
        try {
            val = val.replace(" ", "");
            Float.parseFloat(val);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 检测是否int，否返回0
     *
     * @param value int字符串
     * @return int字符串
     */
    public static String nulltoIntegerDefalt(String value) {
        if (!isIntValue(value)) value = "0";
        return value;
    }

    private static boolean isIntValue(String val) {
        try {
            val = val.replace(" ", "");
            Integer.parseInt(val);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 十六进制byte转十进制数字
     *
     * @param src byte
     * @return 十进制数字
     */
    public static int bytesToInt(byte src) {
        int x1, x2;
        x1 = (src & 0xF0) >> 4;
        x2 = (src & 0x0F);
        return x1 * 16 + x2;
    }

    /**
     * 十六进制byte转十进制数字
     *
     * @param src bytes
     * @return 十进制double
     */
    public static double bytesToDouble(byte[] src) {
        int length = src.length * 2;
        int[] ints = new int[length];
        int tempLen = 0;
        for (byte b : src) {
            ints[tempLen] = (b & 0xF0) >> 4;
            ints[tempLen + 1] = (b & 0x0F);
            tempLen += 2;
        }
        double result = 0;
        for (int anInt : ints) {
            length--;
            result += anInt * Math.pow(16, length);
        }
        return result;
    }


}
