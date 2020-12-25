package WorkDayCalculateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description WorkDayCalculateUtils
 * @since 2020/12/25 14:44
 */
public class WorkDayCalculateUtils {
    /**
     * 特殊的工作日(星期六、日工作)
     */
    private static final List<String> SPECIAL_WORK_DAYS = new ArrayList<>();

    /**
     * 特殊的休息日(星期一到五休息)
     */
    private static final List<String> SPECIAL_REST_DAYS = new ArrayList<>();

    //提前把特殊的工作休息日加载进来
    static {
        initSpecialDays();
    }

    /**
     * 计算工作日
     * @param currentDate 当前日期
     * @param days 多少个工作日
     * @return 当前日期过了多少个工作日之后的日期
     */
    public static Date getDate(Date currentDate, int days) {
        //是0个工作日就不用向下走了
        if (days == 0) {
            return currentDate;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int step = days < 0 ? -1 : 1;
        int i = 0;//记录工作日的天数
        int daysAbs = Math.abs(days);
        while (i < daysAbs) {
            calendar.add(Calendar.DATE, step);
            i++;
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                // 周六日如果上班就算1个工作日
                //这天周六日，特殊工作日里没有这一天，则减去这天
                if (!SPECIAL_WORK_DAYS.contains(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd"))) {
                    i--;
                }
            } else {
                // 这天是工作日，但是特殊休息日里包含这一天，则减去这天
                if (SPECIAL_REST_DAYS.contains(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd"))) {
                    i--;
                }
            }
        }

        return calendar.getTime();
    }

    /**
     * 手工维护特殊日(因为是未知的,所以必须手工维护)
     */
    private static void initSpecialDays() {
        SPECIAL_WORK_DAYS.add("2019-09-29");
        SPECIAL_WORK_DAYS.add("2019-10-12");

        SPECIAL_REST_DAYS.add("2020-10-01");
        SPECIAL_REST_DAYS.add("2020-10-02");

        SPECIAL_REST_DAYS.add("2020-10-05");
        SPECIAL_REST_DAYS.add("2020-10-06");
        SPECIAL_REST_DAYS.add("2020-10-07");

    }

    //测试
    public static void main(String[] args) throws ParseException {
        Date date = getDate(DateUtils.parseDate("2020-12-01", "yyyy-MM-dd"), 18);
        System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd"));
    }
}
