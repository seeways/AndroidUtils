package com.jty.kotlin.constant

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * Stored Constant
 */

object MemoryUtils {

    /** BYTE */
    const val BYTE = 1

    /** BYTE * 1024 */
    const val KB = 1024

    /** KB * 1024 */
    const val MB = 1048576

    /** MB * 1024 */
    const val GB = 1073741824

    /** Stored Unit */
    enum class MemoryUnit {
        BYTE,
        KB,
        MB,
        GB
    }
}
