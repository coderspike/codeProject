package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateTimeUtil {
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss"; //正规的声明方式

    //有些jar没引
//    public static Date str2Date(String dateTimeStr, String formatStr) {
////        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
////        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
////        return dateTime.toDate();
//    }
//
//    public static String date2Str(Date date, String formatStr) {
//        if (date == null) {
//            return StringUtils.EMPTY;
//        }
//        DateTime dateTime = new DateTime(date);
//        return dateTime.toString(formatStr);
//    }
//
//    public static Date str2Date(String dateTimeStr) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
//        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
//        return dateTime.toDate();
//    }
//
//    public static String date2Str(Date date) {
//        if (date == null) {
//            return StringUtils.EMPTY;
//        }
//        DateTime dateTime = new DateTime(date);
//        return dateTime.toString(STANDARD_FORMAT);
//    }

    /**
     * 获取两个日期之间的工作日数 //一些额外方法，省的找了
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public static int getWorkDays(String startTime, String endTime) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begDate = sdf.parse(startTime);
        Date endDate = sdf.parse(endTime);
        if (begDate.after(endDate))
            throw new Exception("日期范围非法");
        // 总天数
        int days = (int) ((endDate.getTime() - begDate.getTime()) / (24 * 60 * 60 * 1000)) + 1;
        // 总周数，
        int weeks = days / 7;
        int rs = 0;
        // 整数周
        if (days % 7 == 0) {
            rs = days - 2 * weeks;
        } else {
            Calendar begCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            begCalendar.setTime(begDate);
            endCalendar.setTime(endDate);
            // 周日为1，周六为7
            int beg = begCalendar.get(Calendar.DAY_OF_WEEK);
            int end = endCalendar.get(Calendar.DAY_OF_WEEK);
            if (beg > end) {
                rs = days - 2 * (weeks + 1);
            } else if (beg < end) {
                if (end == 7) {
                    rs = days - 2 * weeks - 1;
                } else {
                    rs = days - 2 * weeks;
                }
            } else {
                if (beg == 1 || beg == 7) {
                    rs = days - 2 * weeks - 1;
                } else {
                    rs = days - 2 * weeks;
                }
            }
        }
        System.out.println("工作日：" + rs);
        return rs;
    }

    /**
     * 获取实际天数
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static int getRealDays(String startTime, String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begDate = sdf.parse(startTime);
        Date endDate = sdf.parse(endTime);
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(begDate);
        end.setTime(endDate);
        if (start.after(end)) {
            Calendar swap = start;
            start = end;
            end = swap;
        }
        int days = end.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR);
        int year = end.get(Calendar.YEAR);
        if (start.get(Calendar.YEAR) != year) {
            start = (Calendar) start.clone();
            do {
                days += start.getActualMaximum(Calendar.DAY_OF_YEAR);
                start.add(Calendar.YEAR, 1);
            } while (start.get(Calendar.YEAR) != year);
        }
        System.out.println("实际天数：" + days);
        return days;
    }

}


