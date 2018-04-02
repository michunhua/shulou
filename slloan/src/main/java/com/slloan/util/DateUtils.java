package com.slloan.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 作者: fenny
 * 邮箱: 893146555@qq.com
 * 时间: 2017/10/19 10:44
 * 描述: 日期处理
 */
public class DateUtils {

    private static final Calendar CALENDAR = Calendar.getInstance();

    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS_SSS_FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS_FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat YYYY_MM_DD_FULL = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    public static SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");

    public static SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");

    public static SimpleDateFormat HH_MM = new SimpleDateFormat("HH:mm");

    /**
     * @param time   时间
     * @param amount 正数:多少月后,负数:多少月前
     * @return to amount
     */
    public static Date operateMonth(Date time, int amount) {
        return operateDate(Calendar.MONTH, time, amount);
    }

    /**
     * @param time   时间
     * @param amount 正数:多少天后,负数:多少天前
     * @return to amount
     */
    public static Date operateDay(Date time, int amount) {
        return operateDate(Calendar.DATE, time, amount);
    }

    /**
     * @param time   时间
     * @param amount 正数:多少小时后,负数:多少小时前
     * @return to amount
     */
    public static Date operateHour(Date time, int amount) {
        return operateDate(Calendar.HOUR, time, amount);
    }

    /**
     * @param time   时间
     * @param amount 正数:多少分钟后,负数:多少分钟前
     * @return to amount
     */
    public static Date operateMinute(Date time, int amount) {
        return operateDate(Calendar.MINUTE, time, amount);
    }


    public static Date operateDate(int field, Date time, int amount) {
        CALENDAR.setTime(time);
        CALENDAR.add(field, amount);
        return CALENDAR.getTime();
    }

    /**
     * @param dateStr 字符串时间
     * @param pattern 格式
     * @param isEqual 是否判断相等
     * @return 比较当前时间与传入的时间大小
     */
    public static boolean compareNowDate(String dateStr, String pattern, boolean isEqual) {
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            //格式化当前日期
            Date nowDate = df.parse(df.format(new Date()));
            Date compareDate = df.parse(dateStr);
            if (isEqual) {
                //判断当前时间是否大于或等于传入的时间
                return !nowDate.before(compareDate);
            } else {
                //判断当前时间是否大于传入的时间
                return nowDate.after(compareDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param compareDate 字符串时间
     * @param pattern     格式
     * @param isEqual     是否判断相等
     * @return 比较当前时间与传入的时间大小
     */
    public static boolean compareNowDate(Date compareDate, String pattern, boolean isEqual) {
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            Date nowDate = df.parse(df.format(new Date()));//格式化当前日期
            if (isEqual) {
                //判断当前时间是否大于或等于传入的时间
                return !nowDate.before(compareDate);
            } else {
                //判断当前时间是否大于传入的时间
                return nowDate.after(compareDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 传入的时间是否过期
     *
     * @param time 日期毫秒数
     * @param lit  多久过期
     * @param unit 单位
     * @return true 过期
     */
    public static boolean isExpire(long time, int lit, TimeUnit unit) {
        long v = System.currentTimeMillis() - time;
        switch (unit) {
            case MINUTES:
                v = v / 1000 / 60;
                return v >= lit;
            default:
                break;
        }
        return true;
    }
    
    // yyyy-MM-dd HH:mm:ss
 	public static String getInDateTime(Date date) {
 		return YYYY_MM_DD_HH_MM_SS_FULL.format(date);
 	}
 	
    public static void main(String[] args) throws ParseException {
    	String ssss="1483088260";
    	Long long1 = Long.valueOf(ssss);
    	
    	Date dat=new Date(long1*1000);  
    	System.out.println("2222: "+dat);
    	
    	String time = getInDateTime(dat);
    	System.out.println("2222: "+time);
    	System.out.println("getInDateTime="+time);//2018-01-06 11:07:21
    	String  xx="2018-01-06 11:07:21";
    	Date parse = YYYY_MM_DD_HH_MM_SS_FULL.parse(xx);
    	System.out.println(parse);
    	String dateTime = DateUtils.getInDateTime((new Date()));
    	System.out.println("dateTime2222222="+dateTime);
	}
}
