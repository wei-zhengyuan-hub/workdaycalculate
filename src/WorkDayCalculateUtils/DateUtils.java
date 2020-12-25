package WorkDayCalculateUtils;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wzy
 * @version 0.0.3
 * @description DateUtils
 * @since 2020/12/25 15:17
 */
public class DateUtils {

    public static Date parseDate(String str,String... pattern){


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date parse = formatter.parse(str, pos);
        for (String format:pattern){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.format(parse);
            System.out.println(simpleDateFormat.format(parse));
        }
        return parse;
    }

}
