package homework.students.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToDate(String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("invalid date format.");
        }
        return null;
    }


    public static String dateToString(Date date) {
        return sdf.format(date);
    }
}