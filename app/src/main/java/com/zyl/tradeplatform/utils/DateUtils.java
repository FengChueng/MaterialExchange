package com.zyl.tradeplatform.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhouyou on 2016/6/24.
 * Class desc:
 *
 * 日期操作工具类
 */
public class DateUtils {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 时间戳转字符串
     * @param ctime 时间戳
     * @return
     */
    public static String getDateStr(String ctime) {
        String strTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        long lcTime = Long.valueOf(ctime);
        strTime = sdf.format(new Date(lcTime * 1000L));
        return strTime;
    }

    /**
     * 时间戳转字符串
     * @param ctime 时间戳
     * @param format 返回的日期格式，如：yyyy-MM-dd
     * @return
     */
    public static String getDateStr(String ctime, String format) {
        String strTime = "";
        if(TextUtils.isEmpty(ctime)) return strTime;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        long lcTime = Long.valueOf(ctime);
        strTime = sdf.format(new Date(lcTime * 1000L));
        return strTime;
    }

    /**
     * 将时间字符串转换成Date
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 时间戳转换成日期格式字符串
     * @param time 精确到秒的字符串
     * @param format 时间格式
     * @return
     */
    public static String timeStamp2Date(String time, String format) {
        if(time == null || time.isEmpty() || time.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(time+"000")));
    }
    /**
     * 时间戳转换成日期格式字符串
     * @param time 精确到秒的字符串
     * @param format 时间格式
     * @return
     */
    public static String timeStamp(String time, String format) {
        if(time == null || time.isEmpty() || time.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(time+"000")));
    }

    /**
     * 时间戳转换成日期格式字符串
     * @param time 精确到秒的字符串
     * @param format 时间格式
     * @return
     */
    public static String timeStampmomth(String time, String format) {
        if(time == null || time.isEmpty() || time.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy.MM.dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(time+"000")));
    }
    /**
     * 时间戳转换成日期格式字符串
     * @param time 精确到秒的字符串
     * @param format 时间格式
     * @return
     */
    public static String timeStampmomth_2(String time, String format) {
        if(time == null || time.isEmpty() || time.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(time+"000")));
    }
    /**
     * 时间戳转换成日期格式字符串
     * @param time 精确到秒的字符串
     * @param format 时间格式
     * @return
     */
    public static String timeStampmomths(String time, String format) {
        if(time == null || time.isEmpty() || time.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy年MM月dd日";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(time+"000")));
    }
    /**
     * 时间戳转换成日期格式字符串
     * @param time 精确到秒的字符串
     * @param format 时间格式
     * @return
     */
    public static String timeStampmilloin(String time, String format) {
        if(time == null || time.isEmpty() || time.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy.MM.dd HH";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(time+"000")));
    }
    /**
     * 时间戳转换成日期格式字符串
     * @param time 精确到秒的字符串
     * @param format 时间格式
     * @return
     */
    public static String timeStampHour(String time, String format) {
        if(time == null || time.isEmpty() || time.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "HH";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(time+"000")));
    }

    /**
     * 将字符串转日期
     * @param str 字符串
     * @return 日期
     */
    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    /**
     * 将时间字符串转换成Calendar
     * @param str
     * @param format
     * @return
     */
    public static Calendar str2Calendar(String str, String format) {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * 将字符串转Calendar
     * @param str
     * @return
     */
    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    /**
     * 日期转字符串
     * @param c Calendar
     * @param format 转换规则：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    /**
     * 日期转字符串
     * @param d 日期date
     * @param format 转换规则：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2Str(Date d, String format) {
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    /**
     * 获得当前日期的字符串格式(默认：2016-05-01)
     * @return
     */
    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH) + "-"
                + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
                + ":" + c.get(Calendar.SECOND);
    }

    /**
     * 获得当前日期的字符串格式（自定义）
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    /**
     *  获得当前日期的字符串格式,格式到秒
     *
     * @return time -> yyyy-MM-dd-HH-mm-ss
     */
    public static String getMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);
    }

    /**
     * 格式到天
     *
     * @return time -> yyyy-MM-dd
     */
    public static String getDay(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 格式到毫秒
     *
     * @return time -> yyyy-MM-dd-HH-mm-ss-SSS
     */
    public static String getSMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);
    }

    /**
     * 输入的是String，格式诸如20120102，实现加一天的功能，返回的格式为String，诸如20120103
     */
    public static String strAddOneDay(String str) throws ParseException {
        String year = str.substring(0, 4);
        String month = str.substring(4, 6);
        String day = str.substring(6);
        String date1 = year + "-" + month + "-" + day;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(date1);
        Calendar cd = Calendar.getInstance();
        cd.setTime(startDate);
        cd.add(Calendar.DATE, 1);
        String dateStr = sdf.format(cd.getTime());
        String year1 = dateStr.substring(0, 4);
        String month1 = dateStr.substring(5, 7);
        String day1 = dateStr.substring(8);
        return year1 + month1 + day1;
    }

    /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     * @param   timeStr   时间戳
     * @return
     */
    public static String getStandardDate(String timeStr) {
        StringBuffer sb = new StringBuffer();
        long t = Long.parseLong(timeStr);
        long time = System.currentTimeMillis() - (t*1000);
        long mill = (long) Math.ceil(time /1000);//秒前

        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前

        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时

        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前

        if (day - 1 > 0) {
            sb.append(timeStamp(timeStr,"yyyy-MM-dd HH:mm:ss"));
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                sb.append("1天前");
            } else {
                sb.append(hour + "小时前");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时前");
            } else {
                sb.append(minute + "分钟前");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                sb.append("1分钟前");
            } else {
                sb.append(mill + "秒前");
            }
        } else {
            sb.append("刚刚");
        }
        return sb.toString();
    }

    /**
     * 输入的是String，格式诸如20120102，实现减一天的功能，返回的格式为String，诸如20120101
     */
    public static String strDecreaseOneDay(String row) throws ParseException {
        String year = row.substring(0, 4);
        String month = row.substring(4, 6);
        String day = row.substring(6);
        String date1 = year + "-" + month + "-" + day;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(date1);
        Calendar cd = Calendar.getInstance();
        cd.setTime(startDate);
        cd.add(Calendar.DATE, -1);
        String dateStr = sdf.format(cd.getTime());
        String year1 = dateStr.substring(0, 4);
        String month1 = dateStr.substring(5, 7);
        String day1 = dateStr.substring(8);
        return year1 + month1 + day1;
    }

    /**
     * 输入的格式为String，诸如20120101，返回的格式为String，诸如2012-01-01
     */
    public static String stringDateChange(String date) {
        if (date.length() == "20120101".length()) {
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6);
            return year + "-" + month + "-" + day;
        } else {
            return date;
        }
    }

    /**
     * 获取昨天 Data
     * @param date
     * @return
     */
    public static Date getLastdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取明天Date
     * @param date
     * @return
     */
    public static Date getNextdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 判断是否是同一天
     * @param one
     * @param another
     * @return
     */
    public static boolean isTheSameDay(Date one, Date another) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(one);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(another);
        int oneDay = calendar.get(Calendar.DAY_OF_YEAR);
        int anotherDay = calendar2.get(Calendar.DAY_OF_YEAR);
        return oneDay == anotherDay;
    }

    /**
     * 通过年份和月份 得到当月的日子
     */
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
                    return 29;
                }else{
                    return 28;
                }
            default:
                return  -1;
        }
    }

    /**
     * 返回当前月份1号位于周几
     * @param year
     * 		年份
     * @param month
     * 		月份，传入系统获取的，不需要正常的
     * @return
     * 	日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    public static int getFirstDayWeek(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
