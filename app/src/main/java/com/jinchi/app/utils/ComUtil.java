package com.jinchi.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ComUtil {

    /**
     * 用于被selectByExample选择出来的单个的值
     *
     * @param list
     * @return
     */
    public static Object getOne(List list) {
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     * string转dare
     *
     * @param stringDate 时间字符串
     * @param dateFormat 时间格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date getDate(String stringDate, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Date ans = null;
        try {
            ans = df.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ans;
    }

    /**
     * date转string
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String dateToString(Date date, String dateFormat) {
        if (date == null)
            return "";
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    /**
     * date类转localdatetime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        return localDateTime;
    }

    /**
     * localdatetime转date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(localDateTime.atZone(zoneId).toInstant());
        return date;
    }

    /**
     * string转localdatetime
     *
     * @param date
     * @param format
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.now();
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * localdatetime转string
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }

    /**
     * 获取今天开始时刻
     */
    public static LocalDateTime getTodayStart() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 获取今天结束时刻
     */
    public static LocalDateTime getTodayEnd() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }
}
