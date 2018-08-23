package util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName BirthdayFormatUtil
 * @Description TODO
 * @Author 39446
 * @Date 2018/8/21 10:16
 * @Version 1.0
 **/
public class BirthdayFormatUtil {
    /**
     * 时间字段最短长度
     */
    public static int MIN_TIME_LENGTH = 4;
    /**
     * 日期分隔符
     */
    public static String BIRTHDAY_SPILT = "-";

    /**
     * 分割后年月日长度
     */
    public static int YEAR_MONTY_DAY = 3;
    /**
     * 分割后日月长度
     */
    public static int MONTY_DAY = 2;

    /**
     * 不合法年的开头数字
     */
    public static String YEAR_START = "0";

    /**
     * 判断参数是否为时间
     *
     * @param birthday
     * @return
     */
    public static boolean isDate(String birthday) {
        if (StringUtils.isEmpty(birthday) || birthday.length() < MIN_TIME_LENGTH) {
            return false;
        }
        if (!birthday.contains(BIRTHDAY_SPILT) && birthday.length() > MIN_TIME_LENGTH) {
            return false;
        }
        return true;
    }

    /**
     * 格式化时间方法，如果是时间格式就处理
     *
     * @param birthday
     * @return
     */
    public static String formatBirthday(String birthday) {
        String formatBirthday = "";
        if (BirthdayFormatUtil.isDate(birthday)) {
            if (!birthday.contains(BIRTHDAY_SPILT)) {
                return birthday + "年";
            }
            String[] birthdayStyle = birthday.split(BIRTHDAY_SPILT);
            if (YEAR_MONTY_DAY == birthdayStyle.length) {
                formatBirthday = birthdayStyle[0] + "年" + Integer.parseInt(birthdayStyle[1]) + "月" + Integer.parseInt(birthdayStyle[2]) + "日";
                if (!StringUtils.isNumeric(birthdayStyle[0]) || birthdayStyle[0].startsWith(YEAR_START)) {
                    formatBirthday = Integer.parseInt(birthdayStyle[1]) + "月" + Integer.parseInt(birthdayStyle[2]) + "日";
                }
                return formatBirthday;
            }
            if (MONTY_DAY == birthdayStyle.length) {
                formatBirthday = Integer.parseInt(birthdayStyle[0]) + "月" + Integer.parseInt(birthdayStyle[1]) + "日";
                return formatBirthday;
            }
        }
        return formatBirthday;
    }

    /**
     * 经统计时间格式样式大致如下：
     * NULL
     * 空
     * 0000-01-02
     * 01-1
     * 01-02
     * 10
     * 1810-02-1
     * 1921
     * 32068
     * 未知-09-11
     * 未知
     */
    public static void main(String[] args) {
        System.out.println(BirthdayFormatUtil.formatBirthday(""));
        System.out.println(BirthdayFormatUtil.formatBirthday(null));
        System.out.println("0000-01-02/" + BirthdayFormatUtil.formatBirthday("0000-01-02"));
        System.out.println("01-1/" + BirthdayFormatUtil.formatBirthday("01-1"));
        System.out.println("10/" + BirthdayFormatUtil.formatBirthday("10"));
        System.out.println("1810-02-1/" + BirthdayFormatUtil.formatBirthday("1810-02-1"));
        System.out.println("1921/" + BirthdayFormatUtil.formatBirthday("1921"));
        System.out.println("32068/" + BirthdayFormatUtil.formatBirthday("32068"));
        System.out.println("未知-09-11/" + BirthdayFormatUtil.formatBirthday("未知-09-11"));
        System.out.println("未知" + BirthdayFormatUtil.formatBirthday("未知"));
    }
}
