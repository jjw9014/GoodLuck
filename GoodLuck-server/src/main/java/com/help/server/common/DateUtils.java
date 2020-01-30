
package com.help.server.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    private static String format = "yyyy-MM-dd HH:mm:ss";
    private static String formatYYYYMM = "yyyyMM";
    private static String formatYYYYMMDD = "yyyyMMdd";
    private static String formatYYYYMMDD_HHMM = "yyyyMMdd HHmm";
    private static String formatYYYYMMDDHHMM = "yyyyMMddhhmm";
    private static String formatyyyymmdd = "yyyy/MM/dd";
    private static String defaultFormat = "yyyy-MM-dd";
    private static String defaultFormatyyyyMMddHHmmss = "yyyyMMddHHmmss";
    private static String formatYYYYPMMPDD = "yyyy.MM.dd";
    private static String formatMMdd_HHMM = "MM月dd日 HH:mm";

    public DateUtils() {
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        return (new SimpleDateFormat(format)).format(date);
    }

    public static String formatDateYYYYMMDD(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat dfYYYYMMDD = new SimpleDateFormat(formatYYYYMMDD);
        return dfYYYYMMDD.format(date);
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date parseDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            return df.parse(date);
        } catch (ParseException var3) {
            throw new RuntimeException("日期格式错误，转换日期对象失败");
        }
    }

    public static String formatDate2(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String formatDateByPattern(Date date, String pattern) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static Date parseDate2(String date) {
        SimpleDateFormat df2 = new SimpleDateFormat(defaultFormatyyyyMMddHHmmss);

        try {
            return df2.parse(date);
        } catch (ParseException var3) {
            throw new RuntimeException("日期格式错误，转换日期对象失败");
        }
    }

    public static Date parseDate3(String date) throws ParseException {
        SimpleDateFormat df3 = new SimpleDateFormat(formatYYYYMMDD_HHMM);
        return df3.parse(date);
    }

    public static List<Date[]> splitTimeByHours(Date start, Date end, int hours) {
        ArrayList dl;
        Date _end;
        for(dl = new ArrayList(); start.compareTo(end) < 0; start = _end) {
            _end = addHours(start, hours);
            if (_end.compareTo(end) > 0) {
                _end = end;
            }

            Date[] dates = new Date[]{(Date)start.clone(), (Date)_end.clone()};
            dl.add(dates);
        }

        return dl;
    }

    public static List<Date[]> splitTimeBySeconds(Date start, Date end, int Seconds) {
        ArrayList dl;
        Date _end;
        for(dl = new ArrayList(); start.compareTo(end) < 0; start = addSeconds(_end, 1)) {
            _end = addSeconds(start, Seconds);
            if (_end.compareTo(end) > 0) {
                _end = end;
            }

            Date[] dates = new Date[]{(Date)start.clone(), (Date)_end.clone()};
            dl.add(dates);
        }

        return dl;
    }

    public static Date getTimeOf000000() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(5, 0);
        return cal.getTime();
    }

    public static Date getYestoday235959() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimeOf000000());
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, -1);
        cal.set(14, 0);
        cal.add(5, 0);
        return cal.getTime();
    }

    public static Date getNowDay235959() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 23, 59, 59);
        return cal.getTime();
    }

    public static Date addDays(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, amount);
        return c.getTime();
    }

    public static Date addHours(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(11, amount);
        return c.getTime();
    }

    public static Date addMonths(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(2, amount);
        return c.getTime();
    }

    public static Date addSeconds(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(13, amount);
        return c.getTime();
    }

    public static Date addMinus(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(12, amount);
        return c.getTime();
    }

    public static String getYYYYMM() {
        return getYYYYMM(new Date());
    }

    public static String getYYYYMMDD() {
        return getYYYYMMDD(new Date());
    }

    public static String getYYYYMM(Date date) {
        return (new SimpleDateFormat(formatYYYYMM)).format(date);
    }

    public static String getYYYYMMDD(Date date) {
        return (new SimpleDateFormat(formatYYYYMMDD)).format(date);
    }

    public static String getYYYYMMDDHHMM() {
        return (new SimpleDateFormat(formatYYYYMMDDHHMM)).format(new Date());
    }

    public static String getyyyymmdd(Date date) {
        return (new SimpleDateFormat(formatyyyymmdd)).format(date);
    }

    public static String formatDateToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat f = new SimpleDateFormat(defaultFormat);
        return f.format(date);
    }

    public static Date getDate(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time * 1000L);
        return cal.getTime();
    }

    public static Date getTimeOf000000(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(5, 0);
        return cal.getTime();
    }

    public static long getNumberOfSeconds(Date startDay, Date endDay) {
        return (endDay.getTime() - startDay.getTime()) / 1000L;
    }

    public static String getMMddHHmm(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            Date date = df.parse(dateStr);
            return (new SimpleDateFormat(formatMMdd_HHMM)).format(date);
        } catch (ParseException var3) {
            throw new RuntimeException("日期格式错误，转换日期对象失败");
        }
    }

    public static void main(String[] args) throws ParseException {
        Date curDate = new Date();
        Date endDate = addDays(getYestoday235959(), 4);
        System.out.println(curDate.compareTo(endDate));
    }
}
