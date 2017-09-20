package com.jty.kotlin.constant

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * Stored Constant
 */

object MemoryUtils {
    /******************** 存储相关常量  */
    /**
     * Byte与Byte的倍数
     */
    const val BYTE = 1
    /**
     * KB与Byte的倍数
     */
    const val KB = 1024
    /**
     * MB与Byte的倍数
     */
    const val MB = 1048576
    /**
     * GB与Byte的倍数
     */
    const val GB = 1073741824

    enum class MemoryUnit {
        BYTE,
        KB,
        MB,
        GB
    }
}
