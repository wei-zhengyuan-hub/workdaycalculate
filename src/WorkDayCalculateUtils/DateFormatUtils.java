package WorkDayCalculateUtils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wzy
 * @version 0.0.3
 * @description DateFormatUtils
 * @since 2020/12/25 14:57
 */
public class DateFormatUtils {

    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String format = formatter.format(date);
        return format;
    }
}
