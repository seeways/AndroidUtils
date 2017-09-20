package com.jty.mykotlinutils.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Title: DateUtil
 * @Description: 日期工具类
 * @author zjh
 * @date 2017/06/07 10:20
 * @version v1.0
 */
object DateUtils {
    const val DF_yyyyMMdd = "yyyyMMdd"
    const val DF_YYYYMMDD = "yyyy-MM-dd"
    const val DF_YYYYMM = "yyyy-MM"
    const val DF_YYYYmm = "yyyyMM"
    const val DF_YYYY = "yyyy"
    const val DF_YYYYMMDDHH = "yyyy-MM-dd HH"
    const val DF_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm"
    const val DF_YYYYMMDDHHMMSSS = "yyyy-MM-dd HH:mm:sss"
    const val DF_YYYYMMDDHHMMSS_CH = "yyyy年MM月dd日 HH时mm分ss秒"
    const val DF_YYYYMMDD_CH = "yyyy年MM月dd日"
    const val DF_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss"


    fun formatDate(date: Date, pattern: String): String = SimpleDateFormat(pattern).format(date)
    fun formatDate(date: Date, dateFormat: DateFormat): String = dateFormat.format(date)
    /**
     * @param startDate 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
     * @param endDate 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年
     * @return
     * 举例：
     * compareDate("2009-09-12", null, 0);//比较天
     * compareDate("2009-09-12", null, 1);//比较月
     * compareDate("2009-09-12", null, 2);//比较年  //不足一年算一年
     */
    fun compareDate(startDate: Date?, endDate: Date, stype: Int): Int {
        if (null == startDate) {
            return 0
        }
        val startDateStr = formatDate(startDate, DF_YYYYMMDD)
        val endDateStr = formatDate(endDate, DF_YYYYMMDD)

        return compareDate(startDateStr, endDateStr, stype)
    }

    /**
     * @param startDateStr 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
     * @param endDateStr 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年
     * @return
     * 举例：
     * compareDate("2009-09-12", null, 0);//比较天
     * compareDate("2009-09-12", null, 1);//比较月
     * compareDate("2009-09-12", null, 2);//比较年
     */
    fun compareDate(startDateStr: String?, endDateStr: String?, stype: Int): Int {
        var endDateStr = endDateStr
        if (null == startDateStr) {
            return 0
        }
        var n = 0
        val u = arrayOf("天", "月", "年")
        val formatStyle = if (stype == 1) "yyyy-MM" else "yyyy-MM-dd"

        endDateStr = if (endDateStr == null) getCurrentDate("yyyy-MM-dd") else endDateStr

        var df: DateFormat = SimpleDateFormat(formatStyle)

        val c1 = Calendar.getInstance()
        val c2 = Calendar.getInstance()

        try {
            c1.time = df.parse(startDateStr)
            c2.time = df.parse(endDateStr)
        } catch (e3: Exception) {
            df = SimpleDateFormat("yyyyMMdd")//特殊情况,执业时间起始
            try {
                c1.time = df.parse(startDateStr)
                c2.time = df.parse(endDateStr)
            } catch (e: ParseException) {
                println("wrong occured again!")
            }

            //	        System.out.println("wrong occured");
        }

        //List list = new ArrayList();
        while (!c1.after(c2)) {                   // 循环对比，直到相等，n 就是所要的结果
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来
            n++
            if (stype == 1) {
                c1.add(Calendar.MONTH, 1)          // 比较月份，月份+1
            } else {
                c1.add(Calendar.DATE, 1)           // 比较天数，日期+1
            }
        }
        n = n - 1
        if (stype == 2) {
            n = n.toInt() / 365
            n = if (n == 0) 1 else n//不足一年算一年
        }
        //	    System.out.println(startDateStr+" -- "+endDateStr+" 相差多少"+u[stype]+":"+n);

        return n
    }

    /**
     * 当前格式化时间
     * @param format "yyyy-MM-dd"
     * @return
     */
    fun getCurrentDate(format: String): String {
        val day = Calendar.getInstance()
        day.add(Calendar.DATE, 0)
        val sdf = SimpleDateFormat(format)//"yyyy-MM-dd"

        return sdf.format(day.time)
    }

    /**
     * 年份添加
     * @param date
     * @param year
     * @return
     */
    fun dateAddByYear(date: Date, year: Int): Date {
        var date = date
        val calendar:Calendar = GregorianCalendar()
        calendar.time = date
        calendar.add(Calendar.YEAR, year)// 把年份往后增加月份.整数往后推,负数往前移动
        date = calendar.time
        return date
    }

    /**
     * 月份添加
     * @param date
     * @param month
     * @return
     */
    fun dateAddByMonth(date: Date, month: Int): Date {
        var date = date
        //Date date = new Date();// 取时间
        val calendar = GregorianCalendar()
        calendar.time = date
        calendar.add(Calendar.MONTH, month)// 把日期往后增加月份.整数往后推,负数往前移动
        date = calendar.time
        return date
    }

    /**
     * 天数添加
     * @param date
     * @param days
     * @return
     */
    fun dateAddByDay(date: Date, days: Int): Date {
        //Date date = new Date();// 取时间
        val calendar = GregorianCalendar()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, days)// 把日期往后增加天数.整数往后推,负数往前移动
        return calendar.time
    }

    /**
     * Parse a datetime string.
     * @param param datetime string, pattern: "yyyy-MM-dd HH:mm:ss".
     * @return java.util.Date
     */
    fun parse(param: String?): Date? {
        var date: Date? = null
        if (param == null) {
            return null
        } else {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            try {
                date = sdf.parse(param)
            } catch (ex: ParseException) {
                ex.printStackTrace()
            }

            return date
        }
    }

    val datea: Date?
        get() = null


    /**
     *
     * 方法描述：判断日期是否周末双休日
     *
     * @param date
     * @return flag
     */
    fun isWeekends(date: Date): Boolean {
        var flag = true

        val cal = Calendar.getInstance() //日历
        cal.time = date
        val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)//周几
        if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
            flag = false
        }

        return flag
    }

    /**
     *
     * 验证日期是否是节假日
     * @param date  传入需要验证的日期
     * @return
     * return boolean    返回类型  返回true是节假日，返回false不是节假日
     * throws
     */
    fun isHoliday(date: Date, holidayList: List<Date>): Boolean {
        //判断日期是否是节假日
        val calendar = Calendar.getInstance()
        calendar.time = date
        for (tempdate in holidayList) {
            val tempCalendar = Calendar.getInstance()
            tempCalendar.time = tempdate
            if (tempCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                    tempCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) &&
                    tempCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                return true
            }
        }
        return false
    }

    /**
     *
     * 验证日期是否是调休
     * @param date  传入需要验证的日期
     * @return
     * return boolean    返回类型  返回true是调休，返回false不是调休
     * throws
     */
    fun isOffDay(date: Date, offdayList: List<Date>): Boolean {
        //判断日期是否是调休
        val calendar = Calendar.getInstance()
        calendar.time = date
        for (tempdate in offdayList) {
            val tempCalendar = Calendar.getInstance()
            tempCalendar.time = tempdate
            if (tempCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                    tempCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) &&
                    tempCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                return true
            }
        }
        return false
    }

    /**
     *
     * 方法描述：取得当前日期为周几
     *
     * @param date
     * @return flag
     */
    fun getDayOfWeek(date: Date): Int {
        val cal = Calendar.getInstance() //日历
        cal.time = date
        return cal.get(Calendar.DAY_OF_WEEK)//周几
    }

    /**
     *
     * 方法描述：判断时间是否在时间范围内
     *
     * @param date minDate maxDate
     * @return flag
     */
    fun isInTimeRange(date: Date, minDate: Date, maxDate: Date): Boolean {
        var flag = true

        val cal = Calendar.getInstance() //日历
        cal.time = date
        val minCal = Calendar.getInstance() //日历s
        minCal.time = minDate
        val maxCal = Calendar.getInstance() //日历e
        maxCal.time = maxDate

        if (cal.before(minCal) || cal.after(maxCal)) {
            flag = false
        }

        return flag

    }


    /**
     *
     * 方法描述：得到当前月份的第一天
     *
     * @author
     * @return 返回第一天的日期（字符型如：2010-05-01）
     */
    // System.out.println("当前月第一天>>"+df.format(c.getTime())) ;
    val currentMonthFirstDay: String
        get() {

            val c = Calendar.getInstance()
            c.set(Calendar.DATE, 1)

            val df = SimpleDateFormat("yyyy-MM-dd")

            return df.format(c.time)

        }


    /**
     *
     * 方法描述：得到当前月份的最后一天
     *
     * @author
     * @return 返回最后一天的日期（字符型如：2010-05-31）
     */
    //日历
    // 当前月＋1，即下个月
    //得到下个月的月份
    // 将下个月1号作为日期初始值
    // 下个月1号减去一天，即得到当前月最后一天
    //System.out.println("当前月最后一天>>>" + day_end);
    val currentMonthEndDay: String
        get() {

            val cal = Calendar.getInstance()
            cal.add(Calendar.MONTH, 1)
            cal.set(Calendar.DATE, 1)
            cal.add(Calendar.DATE, -1)

            val df = SimpleDateFormat("yyyy-MM-dd")

            return df.format(cal.time)
        }

    /**
     *
     * 方法描述：得到当前月份数值
     *
     * @author guojin
     * @return 返回当前月份数值（字符型如：4）
     */
    //日历
    val currentMonth: Int
        get() {

            val cal = Calendar.getInstance()

            return cal.get(Calendar.MONTH) + 1
        }


    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    fun DateToStr(date: Date): String {

        val format = SimpleDateFormat(DF_YYYYMMDD_CH)
        return format.format(date)
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    fun StrZH(str: String): String {
        var str = str

        val format = SimpleDateFormat(DF_YYYYMMDD)
        var date: Date? = null
        try {
            date = format.parse(str)
            val format2 = SimpleDateFormat(DF_YYYYMMDD_CH)
            str = format2.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str
    }

    /**
     * 获取近七天日期 日期格式yyyyMMdd
     * @return
     * @remark rwl 20161020
     */
    val recentlySevenDay: Array<String?>
        get() {

            val sdf = SimpleDateFormat("yyyyMMdd")
            val strs: Array<String?> = arrayOfNulls<String>(7)
            val c = Calendar.getInstance()
            c.add(Calendar.DATE, -0)
            var monday = c.time
            var preday = sdf.format(monday)
            strs[0] = preday
            for (i in 1..6) {
                c.add(Calendar.DATE, -1)
                monday = c.time
                preday = sdf.format(monday)
                strs[i] = preday
            }

            return strs
        }

    /**
     * 获取某时间之前第60天的日期
     * @param someDate
     * @return
     * @remark rwl 20161118
     */
    fun getBeforeDate(someDate: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = null
        try {
            date = sdf.parse(someDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val calendar:Calendar = GregorianCalendar()

        calendar.time = date!!

        calendar.add(Calendar.DATE, -60)
        date = calendar.time
        return sdf.format(date)
    }


    /**
     * 格式化时间为UTF 时间
     * @param date
     * @return
     */
    @JvmOverloads
    fun formatUTCTime(date: Date = Date()): String {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        df.timeZone = TimeZone.getTimeZone("UTC")
        return df.format(date)
    }





}
