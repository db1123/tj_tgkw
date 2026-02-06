package fun.tools;

import java.util.Date;

public class TimeTools {
    private Long year = (long) (60 * 60 * 24 * 30 * 12); // 1年
    private Long month = (long) (60 * 60 * 24 * 30); // 1月
    private Long day = (long) (60 * 60 * 24); // 1天
    private Long hour = (long) (60 * 60); // 1小时
    private Long minute = 60L; // 1分钟

    /**
     * 获取时间差
     * @param LeftDate 被减数
     * @param RightDate 减数
     * @return
     */
    public Long getTimeDifference(Date LeftDate, Date RightDate) {
        return (LeftDate.getTime() - RightDate.getTime()) / 1000;
    }

    /**
     * 获取修饰后的时间字符串
     * @param time 时间数
     * @return
     */
    public String getYearStr(Long time) {
        return (time / year) + "年";
    }
    public String getMonthStr(Long time) {
        return (time / month) + "月";
    }
    public String getDayStr(Long time) {
        return (time / day) + "天";
    }
    public String getHourStr(Long time) {
        return (time / hour) + "小时";
    }
    public String getMinuteStr(Long time) {
        return (time / minute) + "分钟";
    }
    public String getSecondStr(Long time) {
        return (time) + "秒";
    }

    /**
     * 获取时间差计算结果修饰文本
     * @param LeftDate
     * @param RightDate
     * @return
     */
    public String getTimeTitle(Date LeftDate, Date RightDate) {
        String str = "";
        Long time = getTimeDifference(LeftDate, RightDate);
        if (time < minute) {
            str = getSecondStr(time);
        } else if (time < hour) {
            str = getMinuteStr(time);
        } else if (time < day) {
            str = getHourStr(time);
        } else if (time < month) {
            str = getDayStr(time);
        } else if (time < year) {
            str = getMonthStr(time);
        } else {
            str = getYearStr(time);
        }
        return str;
    }
}