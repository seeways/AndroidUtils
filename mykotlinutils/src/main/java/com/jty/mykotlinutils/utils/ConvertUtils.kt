package com.jty.mykotlinutils.utils

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * Basic Type Convert
 */

object ConvertUtils {
    /**
     * BitMap to Bytes
     *
     * @param bm
     * @return ByteArray
     */
    fun Bitmap2Bytes(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }

    /**
     * String to int
     * @param string
     * @return
     */
    fun stringToInt(string: String): Int = Integer.parseInt(string)


    /**
     * string[] 去空
     */
    fun stringsClear(strings: Array<String>): Array<String> {
        val tmp: MutableList<String> = strings
                .filter { it.isNotEmpty() }
                .toMutableList()
        return tmp.toTypedArray()
    }

    /**
     * strings转List
     * @param strings string[]
     * @param isClear 是否去空
     * @return List
     */
    @JvmOverloads
    fun stringsToList(strings: Array<String>, isClear: Boolean = false): List<Any> {
        val list = ArrayList<Any>()
        for (str in strings) {
            if (isClear) {
                if (str.isNotEmpty()) list.add(str)
            } else {
                list.add(str)
            }
        }
        return list
    }

    /**
     * List转strings
     * @param list
     * @return strings string[]
     */
    fun listToStrings(list: List<String>): Array<String> = list.toTypedArray()


}
