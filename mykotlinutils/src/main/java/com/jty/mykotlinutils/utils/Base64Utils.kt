package com.jty.mykotlinutils.utils

import android.util.Base64
import com.jty.kotlin.constant.MemoryUtils
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * Basic Base64
 */

object Base64Utils {

    /**
     * Base64 Encode
     */
    fun base64Encode(input: ByteArray): ByteArray = Base64.encode(input, Base64.DEFAULT)

    /**
     * Custom Base64 Flags Encode
     */
    fun base64Encode(input: ByteArray, flags: Int): ByteArray = Base64.encode(input, flags)

    /**
     * Base64 Decode
     */
    fun base64Decode(input: ByteArray): ByteArray = Base64.decode(input, Base64.DEFAULT)

    /**
     * Custom Base64 Flags Decode
     */
    fun base64Decode(input: ByteArray, flags: Int): ByteArray = Base64.decode(input, flags)

    /**
     * Bytes Encode To Base64 String
     */
    @Throws(Exception::class)
    fun encode(bytes: ByteArray,flags: Int): String = Base64.encodeToString(bytes,flags)

    /**
     *  Base64 String Decode To Bytes
     */
    @Throws(Exception::class)
    fun decode(base64: String): ByteArray = base64.toByteArray()

    /**
     * byteArray To File
     */
    @Throws(Exception::class)
    fun byteArrayToFile(bytes: ByteArray, filePath: String) {

        val inStream = ByteArrayInputStream(bytes)
        val destFile = File(filePath)
        if (!destFile.parentFile.exists())
            destFile.parentFile.mkdirs()
        destFile.createNewFile()
        val out = FileOutputStream(destFile)
        inStream.copyTo(out,MemoryUtils.KB)
        out.close()
        inStream.close()
    }

    /**
     * File To Bytes
     */
    @Throws(Exception::class)
    fun fileToByte(filePath: String): ByteArray? {

        val file = File(filePath)
        if (file.exists()) {
            return file.readBytes()
        }
        return null
    }

    /**
     *
     */
    @Throws(Exception::class)
    fun encodeFile(filePath: String): String {
        val bytes = fileToByte(filePath)
        return encode(bytes!!,0)
    }

    /**
     * BASE64字符串转回文件
     * @param filePath 文件绝对路径
     * @param base64   编码字符串
     * @throws Exception
     */
    @Throws(Exception::class)
    fun decodeToFile(filePath: String, base64: String) {
        val bytes = decode(base64)
        byteArrayToFile(bytes, filePath)
    }
}
