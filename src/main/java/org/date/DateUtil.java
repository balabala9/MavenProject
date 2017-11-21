package org.date;

import org.DateCommonst;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

public class DateUtil {


    //当前时间格式

    public static String currentDate(String dateFormat) {
        Date curDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(curDate);
    }

    //当前时间戳
    public static String currentTimestamp() {
        Date curDate = new Date();
        long timestamp = curDate.getTime();
        return String.valueOf(timestamp);
    }

    //字符串转时间戳(毫秒)
    public static String dateTotimestampMsec(String date, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        String timestampStr = null;
        try {
            Date date1 = df.parse(date);
            long timestamp = date1.getTime();
            timestampStr = String.valueOf(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestampStr;

    }

    //字符串转时间戳(秒)
    public static String dateTotimestampSecond(String date, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        String timestampStr = null;
        try {
            Date date1 = df.parse(date);
            long timestamp = date1.getTime();
            long secondStamp = timestamp / 1000;
            timestampStr = String.valueOf(secondStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestampStr;

    }

    //日期转时间戳
    public static String dateTotimeMsec(Date date) {
        long timestamp = date.getTime();
        String timestampStr = String.valueOf(timestamp);
        return timestampStr;

    }

    //时间戳转时间字符串
    public static String timestampTodate(String timestamp, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Date date = new Date();
        date.setTime(Long.valueOf(timestamp));
        String dateStr = df.format(date);
        return dateStr;
    }

    //字符串日期转dateFormatAfter格式
    public static String formatDate(String date, String dateFormatPre, String dateFormatAfter) {

        String dateStr = null;
        try {
            SimpleDateFormat dfPre = new SimpleDateFormat(dateFormatPre);
            SimpleDateFormat dfAfter = new SimpleDateFormat(dateFormatAfter);
            Date date1 = dfPre.parse(date);
            dateStr = dfAfter.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;

    }


    //获取指定日期几天后的日期
    public static String afterDate(String date, String dateFormat, int days) {

        String dateStr=null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            Date date1=df.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+days);
            calendar.getTime();
            dateStr=df.format(calendar.getTime());
        }catch (ParseException e){
            e.printStackTrace();
        }

        return dateStr;

    }


    public static void main(String[] args) throws ParseException {

        System.out.println(DateUtil.currentDate(DateCommonst.DATEFORMATE1));

        System.out.println(DateUtil.currentTimestamp());

        System.out.println(DateUtil.dateTotimestampMsec("2017-09-08", DateCommonst.DATEFORMATE2));

        System.out.println(DateUtil.dateTotimestampSecond("2017-09-08", DateCommonst.DATEFORMATE2));

        System.out.println(DateUtil.timestampTodate("1508915798730", DateCommonst.DATEFORMATE1));

        System.out.println(DateUtil.afterDate("2017-09-08", DateCommonst.DATEFORMATE2,2));

    }
}
