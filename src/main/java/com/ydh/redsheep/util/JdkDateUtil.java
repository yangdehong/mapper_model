package com.ydh.redsheep.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @description: jdk8以上的时间工具类
 * @author: yangdehong
 * @date: 2018/4/11 11:17
 */
public class JdkDateUtil {

    /**
     * LocalDateTime ----> DateUtil
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * LocalDate ----> DateUtil
     *
     * @param localDate
     * @return
     */
    public static Date localDateToUdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * DateUtil ----> LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime uDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    /**
     * DateUtil ----> LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate uDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    /**
     * 获取指定月份天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getYearOrMonthDays(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

    public static int getYearOrMonthDays(int year, Month month) {// Month.AUGUST
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

    /**
     * 指定年天数
     *
     * @param year
     * @return
     */
    public static int getYearOrMonthDays(int year) {
        return Year.now().length();
    }

    /**
     * 当前月 天数
     *
     * @return
     */
    public static int getCurrentMonthDays() {
        YearMonth currentYearMonth = YearMonth.now();
        return currentYearMonth.lengthOfMonth();
    }

    /**
     * 当前年 天数
     *
     * @return
     */
    public static int getCurrentYearDays() {
        YearMonth currentYearMonth = YearMonth.now();
        return currentYearMonth.lengthOfYear();
    }

    /**
     * 获取当前年、月、日
     *
     * @return
     */
    public static int getCurrentYear() {
        return YearMonth.now().getYear();
    }
    public static int getCurrentMonth() {
        return YearMonth.now().getMonthValue();
    }
    public static int getCurrentDay() {
        return MonthDay.now().getDayOfMonth();
    }

    /**
     * 当前时间
     * @return
     */
    public static LocalDate currentDate() {
        return LocalDate.now();
    }
    public static LocalDateTime currentTime() {
        return LocalDateTime.now();
    }

    /**
     * 是否周末
     *
     * @return
     */
    public static boolean isWeekend() {
        boolean flag = false;
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            flag = true;
        }
        return flag;
    }

    /**
     * 每周的第一天为礼拜一而不是周日
     * @return  每周第一天
     */
    public static Date getStartOfWeek() {
        LocalDate inputDate = LocalDate.now();
        TemporalAdjuster firstOfWeek = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.MONDAY.getValue()));
        inputDate.with(firstOfWeek);
        return localDate2Date(inputDate);
    }

    /**
     * 当前天之前的days天
     * @param days  向前推移的天数
     * @return  计算后的天数
     */
    public static Date minusDays(Long days) {
        if (days == null) {
            return new Date();
        }
        LocalDate localDate = currentDate();
        localDate = localDate.minusDays(days);
        return localDate2Date(localDate);
    }

    /**
     * LocalDate 转换为 Date
     * @param localDate LocalDate时间
     * @return  Date格式时间
     */
    public static Date localDate2Date(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date getStartOfToday(String date) {
        LocalDate localDate;
        if (date != null) {
            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            localDate = currentDate();
        }


        return localDate2Date(localDate);
    }

    public static Date getEndOfToday(String date) {
        LocalDate localDate;
        if (date != null) {
            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            localDate = currentDate();
        }
        LocalDateTime todayEnd = LocalDateTime.of(localDate, LocalTime.MAX);

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = todayEnd.atZone(zoneId);

        return Date.from(zdt.toInstant());
    }


    public static void main(String[] args) {
        LocalDate localDate = currentDate();
        localDate = localDate.minusDays(11);
        System.out.println(localDate);

    }

}
