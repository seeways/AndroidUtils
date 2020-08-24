package com.jty.myutils.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Time管理类
 * Created by JTY on 2016/8/12 0012.
 */
public class TimeUtils {
    private static Date date = new Date();

    public static void setDate(Date d) {
        date = d;
    }

    public static String getTime(String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getTime(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

    //获取时间戳
    public static String getTimeStemp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    // 获取当日时间戳
    public static String getDayTimeStemp() {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    // 获取当月时间戳
    public static String getMonthStemp() {
        return new SimpleDateFormat("yyyyMM").format(date);
    }

    //获得当前时间
    public static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    //获得当前日期
    public static String getDay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    //获得当月時間
    public static String getMonth() {
        return new SimpleDateFormat("yyyy-MM").format(date);
    }

    //获得当年时间
    public static String getYear() {
        return new SimpleDateFormat("yyyy").format(date);
    }


    //获取东八区区时
    private static Calendar chinaCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
    public static int year = chinaCalendar.get(Calendar.YEAR);
    public static int month = chinaCalendar.get(Calendar.MONTH) + 1;
    public static int days = chinaCalendar.get(Calendar.DAY_OF_MONTH);

    //获取年，年月，年月日
    public static String getDayTime() {
        return year + "/" + month + "/" + days;
    }

    public static String getLikeMonth() {
        return year + "/" + month + "%";
    }

    public static String getLikeYear() {
        return year + "%";
    }

}
