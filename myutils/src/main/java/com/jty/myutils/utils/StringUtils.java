package com.jty.myutils.utils;

/**
 * @author TaoYuan
 * @time 2017/3/17 0017
 * @desc 字符串工具类
 */

public class StringUtils {
    /**
     * 判空(空格也算空)
     * @param s s1
     * @return r
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * 字符串是否相等
     * @param a s1
     * @param b s2
     * @param isIgnoreCase 是否忽略大小写
     * @return r
     */
    public static boolean isEquals(String a, String b,boolean isIgnoreCase) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(isIgnoreCase, 0, b, 0, b.length());
    }


}
