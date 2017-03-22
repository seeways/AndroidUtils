package com.jty.myutils.constant;

/**
 * @author TaoYuan
 * @time 2017/3/22 0022
 * @desc
 */

public class MemoryUtils {
    /******************** 存储相关常量 ********************/
    /**
     * Byte与Byte的倍数
     */
    public static final int BYTE = 1;
    /**
     * KB与Byte的倍数
     */
    public static final int KB   = 1024;
    /**
     * MB与Byte的倍数
     */
    public static final int MB   = 1048576;
    /**
     * GB与Byte的倍数
     */
    public static final int GB   = 1073741824;

    public enum MemoryUnit {
        BYTE,
        KB,
        MB,
        GB
    }
}
