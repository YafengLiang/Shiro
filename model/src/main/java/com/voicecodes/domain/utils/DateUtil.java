package com.voicecodes.domain.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in  11:10 2018/5/31
 */
public class DateUtil {

    private static String formatStr = "yyyy-MM-dd HH:mm:ss";

    public static Date string2Date(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = sdf.parse(dateStr);
        return date;
    }

    public static Date str2Date(String dateStr) throws ParseException {
        String formatStr = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = sdf.parse(dateStr);
        return date;
    }

    public static Date string2Date(String dateStr, String formatStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = sdf.parse(dateStr);
        return date;
    }

    /**
     * @param dateStr 如20160909
     * @return
     */
    public static Date string2Date2(String dateStr) throws ParseException {
        String str = "dd-MM-yy";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date date = sdf.parse(dateStr);
        return date;
    }

  /*  public static String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String dateStr = sdf.format(date);
        return dateStr;
    }*/

    public static String date2Str(Date date, String formatStr) {
        if (date == null) {
            return null;
        }
        if (formatStr == null) {
            formatStr = "yyyy-MM-dd HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static String displayPresentDate(String langType) {
        Date date = new Date();
        Locale locale = null;
        if ("en".equals(langType)) {
            locale = new Locale("en", "US");
        } else if ("zh".equals(langType)) {
            locale = new Locale("zh", "CN");
        } else if ("ja".equals(langType)) {
            locale = new Locale("ja", "JP");
        } else if ("ko".equals(langType)) {
            locale = new Locale("ko", "KR");
        }
        DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
        String displayDate = fullDateFormat.format(date);
        return displayDate;
    }

    //获取当前时间
    public static String getStartTimeCur() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String start = format.format(c.getTime()) + " 23:59:59";
//        System.out.println(start);
        return start;
    }
    //获取一个礼拜前
    public static String getEndTimeCur() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -6);
        String start = format.format(c.getTime())+" 00:00:00";
//        System.out.println(start);
        return start;
    }
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * date是否早于date2
     *
     * @param date1
     * @param date2
     * @return
     * @throws Exception
     */
    public static boolean compareDate(Date date1, Date date2) throws Exception {
        if (date1 == null || date2 == null) {
            throw new Exception("the two date cannot be null");
        }
        return date1.before(date2);
    }


    public static void main(String[] args) throws ParseException {
        Date date1 = new Date();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, -1);
        date1 = instance.getTime();
        Date date2 = new Date();
        System.out.println(getDuration(date1, date2));
    }

    public static long getDuration(Date startDate, Date endDate) {
        return ((endDate.getTime() - startDate.getTime()) / 1000);
    }


}
