package com.jty.mykotlinutils.test

import com.jty.mykotlinutils.utils.DateUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by TaoYuan on 2017/9/20 0020.
 */
fun main(args: Array<String>) {
    val currentTime = DateUtils.formatDate(Date(),SimpleDateFormat.getDateTimeInstance())
    DateUtils.formatDate(Date(),DateUtils.DF_YYYYMMDDHHMMSS)
    println("currentTime==" + currentTime)
}