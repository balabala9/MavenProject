package org.java.date;

import com.alibaba.fastjson.JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    //获取指定月份的第一天 beforFormat 任意　　 afterFormat 任意
    public static String firstDateOfMonth(String dateTime, String beforFormat, String afterFormat) {

        String firstDateOfMonth = null;
        try {
            SimpleDateFormat beforSdf = new SimpleDateFormat(beforFormat);
            Date date = beforSdf.parse(dateTime);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

            SimpleDateFormat afterSdf = new SimpleDateFormat(afterFormat);
            firstDateOfMonth = afterSdf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return firstDateOfMonth;
    }

    //获取指定月份的第一天 beforFormat 任意　　 afterFormat 任意
    public static String endDateOfMonth(String dateTime, String beforFormat, String afterFormat) {

        String endDateOfMonth = null;
        try {
            SimpleDateFormat beforSdf = new SimpleDateFormat(beforFormat);
            Date date = beforSdf.parse(dateTime);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

            SimpleDateFormat afterSdf = new SimpleDateFormat(afterFormat);
            endDateOfMonth = afterSdf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return endDateOfMonth;
    }

    //    获取指定月份的所有日期 dateFormat yyyy-MM
    public static List<String> datesOfMonthForDate(String dateTime, String beforeDateFormat, String afterDateFormat) {
        List<String> list = new ArrayList<>();
        try {
            SimpleDateFormat beforeSdf = new SimpleDateFormat(beforeDateFormat);
            Date date = beforeSdf.parse(dateTime);

            SimpleDateFormat afterSdf = new SimpleDateFormat(afterDateFormat);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            for (int i = day; i <= days; i++) {
                Date curDate = calendar.getTime();
                String strCurDate = afterSdf.format(curDate);
                list.add(strCurDate);

                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取指定时间段的的所有日期　dateFormat yyyy-MM-dd
    public static List<String> datesOfMonthForPeriod(String startTime, String endTime, String dateFormat) {
        List<String> list = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
            //获取endTime的时间
            endCalendar.add(Calendar.DAY_OF_MONTH, 1);

            System.out.println(startCalendar.before(endCalendar));
            while (startCalendar.before(endCalendar)) {

                Date curDate = startCalendar.getTime();
                String strCurDate = sdf.format(curDate);
                list.add(strCurDate);

                startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Date strToDate(String date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date result = null;
        try {
            result = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getDates(List<String> dates, String dateFormat) {
        List<String> list = dates.stream().map(x -> getDate(x, dateFormat)).collect(Collectors.toList());
        return list;
    }

    //根据日期获取日
    public static String getDate(String date, String dateFormat) {
        Date date1 = strToDate(date, dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int days = calendar.get(Calendar.DAY_OF_MONTH);

        return String.valueOf(days);
    }


    public static void main(String[] args) throws ParseException {

//        System.out.println(DateUtil.currentDate(DateCommonst.DATEFORMATE1));
//
//        System.out.println(DateUtil.currentTimestamp());
//
//        System.out.println(DateUtil.dateTotimestampMsec("2017-09-08", DateCommonst.DATEFORMATE2));
//
//        System.out.println(DateUtil.dateTotimestampSecond("2017-09-08", DateCommonst.DATEFORMATE2));
//
//        System.out.println(DateUtil.timestampTodate("1508915798730", DateCommonst.DATEFORMATE1));
//
//        System.out.println(DateUtil.afterDate("2017-09-08", DateCommonst.DATEFORMATE2,2));


//        List<String> list=DateUtil.datesOfMonth("2017-08-01","2017-09-06","yyyy-MM-dd");
//        System.out.println(JSON.toJSONString(list));

        List<String> dates = DateUtil.datesOfMonthForDate("2018-09", "yyyy-MM", "yyyy-MM-dd");
        System.out.println(JSON.toJSONString(dates));

    }
}
