package project.gcs.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Link
 * @Date: 2020/7/30 13:15
 * @Version 1.0
 */
public class DateUtils {

    /**
     * 将日期转为小时
     * 如 2020-7-30 13:00 -> 13
     */
    public static Integer getHourOfDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);

    }

}
