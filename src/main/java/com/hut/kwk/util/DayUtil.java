package com.hut.kwk.util;

/**
 * Create by kwk on 2019-04-25
 *
 * @author kwk
 */
public class DayUtil {
    public static String toDay(int x){
        switch (x){
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            default:
                return null;
        }
    }
    public static String toTime(int x){
        switch (x){
            case 1:
                return "第一节";
            case 2:
                return "第二节";
            case 3:
                return "第三节";
            case 4:
                return "第四节";
            default:
                return null;
        }

    }

    public static Integer toDayNum(String x){
        switch (x){
            case "星期一":
                return 1;
            case "星期二":
                return 2;
            case "星期三":
                return 3;
            case "星期四":
                return 4;
            case "星期五":
                return 5;
            default:
                return null;
        }
    }
}
