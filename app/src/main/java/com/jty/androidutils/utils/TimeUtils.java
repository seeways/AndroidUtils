package com.jty.androidutils.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by JTY on 2016/8/12 0012.
 */
public class TimeUtils {
    //获取时间戳
    public static String getTimeStemp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
    //获得当前时间
    public static String getTime(){
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return time;
    }


    //获取东八区区时
    static Calendar chinaCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
    static int year = chinaCalendar.get(Calendar.YEAR);
    static int month = chinaCalendar.get(Calendar.MONTH) + 1;
    static int days = chinaCalendar.get(Calendar.DAY_OF_MONTH);

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
