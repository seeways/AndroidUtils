package com.jty.myutils.utils.kt

import android.annotation.SuppressLint
import android.util.Log
import com.jty.myutils.utils.FileUtils
import com.jty.myutils.utils.L
import com.jty.myutils.utils.TimeUtils
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * created by Taoyuan on 2021/6/14
 * Log管理类
 */
object L {
    /**
     * Log控制，在application的onCreate函数里面初始化
     */
    var isDebug = true

    private const val TAG = "way"

    /**
     * log等级
     */
    fun i(tag: String = TAG, msg: String) {
        if (isDebug) Log.i(tag, msg)
    }

    fun d(tag: String = TAG, msg: String) {
        if (isDebug) Log.i(tag, msg)
    }

    fun v(tag: String = TAG, msg: String) {
        if (isDebug) Log.i(tag, msg)
    }

    fun e(tag: String = TAG, msg: String) {
        if (isDebug) Log.i(tag, msg)
    }


    /**
     * 存储Log文件
     */
    fun write(
        file: String,
        content: String = "",
        append: Boolean = true,
        needTime: Boolean = true
    ) {
        lateinit var out: BufferedWriter
        try {
            if (FileUtils.createOrExistsFile(file))
                out = BufferedWriter(OutputStreamWriter(FileOutputStream(file, append)))
            else return
            if (needTime) out.write(
                """
                ${TimeUtils.getTime()} 
                $content
                """.trimIndent()
            ) else out.write(content.trimIndent())
            out.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                out.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun readFile(filePath: String?): String {
        val content = StringBuilder() //文件内容字符串
        //打开文件

        //如果path是传递过来的参数，可以做一个非目录的判断
        if (FileUtils.isFileExists(filePath) && FileUtils.isFile(filePath)) {
            try {
                val instream: InputStream = FileInputStream(filePath)
                val inputreader = InputStreamReader(instream)
                val buffreader = BufferedReader(inputreader)
                var line: String?
                //分行读取
                while (buffreader.readLine().also { line = it } != null) {
                    content.append(line).append("\n")
                }
                instream.close()
            } catch (e: FileNotFoundException) {
                L.e("The File doesn't not exist.")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return content.toString()
    }

    //获得当日时间
    @SuppressLint("SimpleDateFormat")
    fun getDay(): String? {
        return SimpleDateFormat("yyyy-MM-dd").format(Date())
    }

}