package com.example.transation.util;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Author ：xzh
 * @Description:
 * @Date: 14:49 2018/4/8
 * @Modified by:
 */
@Slf4j
public class TimeUtil {

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_FORMAT_STR = "yyyyMMddHHmmss";

    /**
     * 整型日期格式
     */

    public static final String INTEGER_FORMAT = "yyyyMMdd";
    /**
     * 字符型日期格式
     */
    public static final String TIME_FORMAT = "MM/dd/yyyy";

    public static final String TIME_FORMAT_CN = "yyyy年MM月dd日";

    public static final String TIME_BEIJING_FORMAT = "MM/dd HH:mm:ss";

    public static final String TIME_BEIJING_FORMAT_1 = "MM/dd HH:mm";

    public static final String TIME_BEIJING_FORMAT_2 = "yyyy-MM-dd HH:mm";

    private static final Integer TIME_STRING_LENGTH = 11;

    public static final SimpleDateFormat TIME_FORMAT_MILLION = new SimpleDateFormat(
            "HH:mm:ss:SSS");

    public static final String TIME_SHORT_FORMAT = "MM/dd";

    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public final static DateTimeFormatter CN_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

    /**
     * 获取系统当前时间戳
     *
     * @return int
     */
    public static int time() {
        return (int) (System.currentTimeMillis() / 1000);
    }


    /**
     * 根据传进来的系统时间获取时间戳
     *
     * @param timeMillis
     * @return
     */
    public static int getTime(long timeMillis) {
        return (int) (timeMillis / 1000);
    }

    /**
     * 获取系统当前时间
     *
     * @return long
     */
    public static long millisTime() {
        return System.currentTimeMillis();
    }

    /**
     * 把当前时间格式化成yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String date() {
        return new SimpleDateFormat(DEFAULT_FORMAT).format(System.currentTimeMillis());
    }

    public static String date(Date date, String timeFormat) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(timeFormat).format(date);
    }

    public static String dateToString(Date date) {
        String dateStr = null;
        if (date != null) {
            Instant instant = date.toInstant();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            dateStr = DATE_TIME_FORMATTER.format(localDateTime);
        }
        return dateStr;
    }

    public static String getFormatTimeStr(Date date, String dateStrFormatter) {
        SimpleDateFormat format = new SimpleDateFormat(dateStrFormatter);
        return format.format(date);
    }

    public static String dateToSimpleString(Date date) {
        String dateStr = null;
        if (date != null) {
            Instant instant = date.toInstant();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            dateStr = DATE_FORMATTER.format(localDateTime);
        }
        return dateStr;
    }

    public static String dateToSimpleCNString(Date date) {
        String dateStr = null;
        if (date != null) {
            Instant instant = date.toInstant();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            dateStr = CN_DATE_FORMATTER.format(localDateTime);
        }
        return dateStr;
    }

    public static Date getDateByString(String time, String timeFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        try {
            Date utilDate = sdf.parse(time);
            return utilDate;
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDateByString(String time) {
        SimpleDateFormat sdf = null;
        if (time.length() < TIME_STRING_LENGTH) {
            sdf = new SimpleDateFormat(TIME_FORMAT);
        } else {
            sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        }
        try {
            Date utilDate = sdf.parse(time);
            return utilDate;
        } catch (Exception e) {
            log.error("时间转换失败！{}", time);
            return null;
        }
    }

    /**
     * 获取5个年份
     *
     * @param date
     * @return
     */
    public static List<Integer> after5Years(LocalDateTime date) {
        if (date == null) {
            date = LocalDateTime.now();
        }
        int nowYears = date.getMonth().getValue() >= Month.AUGUST.getValue() ? date.getYear() : date.getYear() - 1;
        return Lists.newArrayList(nowYears, nowYears - 1, nowYears - 2, nowYears - 3, nowYears - 4);
    }

    public static Date getZeroTime(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
