package org.java.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateBase {

    public static void main(String[] args) throws ParseException {
        /**
         * TimeZone  时区
         */
        System.out.println("TimeZone 时区开始---------------------");

        //TimeZone 时区偏移量
        //使用static TimeZone 	getDefault()  获取默认的TimeZone对象
        TimeZone tz = TimeZone.getDefault();
        System.out.println("时区:" + tz.getID());

        //根据ID 使用static TimeZone 	getTimeZone(String ID)获取TimeZone对象
        TimeZone tz1 = TimeZone.getTimeZone("US/Hawaii");
        System.out.println("DisplayName标准时间名称:" + tz1.getDisplayName());


        //使用 static String[] 	getAvailableIDs() 返回所有TimeZone 的ID
        String[] timeZoneIDs = TimeZone.getAvailableIDs();
        for (String id : timeZoneIDs) {
            System.out.println(id);
        }

        //使用　abstract int 	getRawOffset()获取“时间偏移”。相对于“本初子午线”的偏移，单位是ms 毫秒
        int rawOffset = tz.getRawOffset() / 1000 / 60 / 60;//小时
        System.out.println("rawOffset　时间偏移:" + rawOffset);

        //设置时区
        Date date11 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sdf.setTimeZone(tz1);
        String sdfDate11 = sdf.format(date11);
        System.out.println(sdfDate11);

        System.out.println("TimeZone 时区结束---------------------");


        /**
         * Date 日期
         */
        Date date=new Date();//Tue Oct 24 09:58:54 CST 2017
        //返回此对象的毫秒数
        long time=date.getTime();

        //对象初始设置时间　时间毫秒时间点
//        Date date3=new Date(Long date)

        Date date1= new Date(time);
        date1.setTime(time);
        System.out.println(date);
        System.out.println(date.toString());

        /**
         setTime(long time)   时间毫秒时间点　
         int 	getDate()---------------- Calendar.get(Calendar.DAY_OF_MONTH)

         int 	getDay()----------------------Calendar.get(Calendar.HOUR_OF_DAY)

         int 	getMinutes()-------------------Calendar.get(Calendar.MINUTE)

         int 	getMonth()------------------Calendar.get(Calendar.MONTH)

         int 	getSeconds() ----------------Calendar.get(Calendar.SECOND)

         void 	setYear(int year)----------- Calendar.set(Calendar.YEAR, year + 1900)　设置年

         void 	setMonth(int month)----------------Calendar.set(Calendar.MONTH, int month)  设置月

         void 	setDate(int date)-------------------Calendar.set(Calendar.DAY_OF_MONTH, int date) 设置日

         void 	setHours(int hours)-----------------Calendar.set(Calendar.HOUR_OF_DAY, int hours) 设置小时

         void 	setMinutes(int minutes)-----------------Calendar.set(Calendar.MINUTE, int minutes) 设置分钟

         void 	setSeconds(int seconds)-----------------Calendar.set(Calendar.SECOND, int seconds) 设置秒


        boolean after(Date when)
        测试此日期是否在指定日期之后。

        boolean before(Date when)
        测试此日期是否在指定日期之前。

        Object clone()
        返回此对象的副本。

        int compareTo(Date anotherDate)
        比较两个日期的顺序。

        boolean equals(Object obj)
        比较两个日期的相等性。

        long getTime()
        返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。

        int hashCode()
        返回此对象的哈希码值。

        void setTime(long time)
        设置此 Date 对象，以表示 1970 年 1 月 1 日 00:00:00 GMT 以后 time 毫秒的时间点。

         int 	getTimezoneOffset()--------------(Calendar.get(Calendar.ZONE_OFFSET) + Calendar.get(Calendar.DST_OFFSET)) / (60 * 1000)

        String toString()
        把此 Date 对象转换为以下形式的 String： dow mon dd hh:mm:ss zzz yyyy 其中：
        dow 是一周中的某一天 (Sun, Mon, Tue, Wed, Thu, Fri, Sat)。
        mon 是月份 (Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec)。
        dd 是一月中的某一天（01 至 31），显示为两位十进制数。
        hh 是一天中的小时（00 至 23），显示为两位十进制数。
        mm 是小时中的分钟（00 至 59），显示为两位十进制数。
        ss 是分钟中的秒数（00 至 61），显示为两位十进制数。
        zzz 是时区（并可以反映夏令时）。标准时区缩写包括方法 parse 识别的时区缩写。如果不提供时区信息，则 zzz 为空，即根本不包括任何字符。
        yyyy 是年份，显示为 4 位十进制数。
         */


        //获取系统当前时间的毫秒数
      long currentTimeMillis= System.currentTimeMillis();
      System.out.println(currentTimeMillis);



        /**
         * SimpleDateFormat
         *
         */

        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat df1=new SimpleDateFormat("yyyy ww");
        SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM WW");
        SimpleDateFormat df3=new SimpleDateFormat("yyyy D");

        //日期->字符串
        String str=df.format(date);
        String str1=df1.format(date);
        String str2=df2.format(date);
        String str3=df3.format(date);

        //将符合该格式的字符串转换为日期，若格式不相配，则会出错
        //字符串->日期
        Date date2=df.parse(str);
        System.out.println(date2);


        //设置时区
        TimeZone timeZone = TimeZone.getTimeZone("SystemV/CST6");
        df.setTimeZone(timeZone);


        //返回Calendar对象
        Calendar calendar1 = df.getCalendar();

        /**
         * Calendar 是一个抽象类
         *
         * Calendar中些陷阱，很容易掉下去：
         1、Calendar的星期是从周日开始的，常量值为0。
         2、Calendar的月份是从一月开始的，常量值为0。
         3、Calendar的每个月的第一天值为1。
         */
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        //获取日期
        Date date3= calendar.getTime();

        String str4=df.format(date3);

        System.out.println(str4);
        //当前时间
        System.out.println(calendar.get(Calendar.DATE));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.HOUR));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));
        System.out.println(calendar.get(Calendar.AM_PM));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));


        /**
         * 三、总结
         Java中日期的经常有一下五个方面：
         1、创建日期
         2、日期格式化显示
         3、日期的转换（主要是和字符串之间的相互转换）
         4、日期中年、月、日、时、分、秒、星期、月份等获取。
         5、日期的大小比较、日期的加减。
         这也是学习java日期操作的难点和关键，掌握了这些，日期问题一般难不倒你。
         */



    }
}
