package by.chuvasova.medroom.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class TimeUtils {

    public Date dateConverter(String date) throws ParseException {
        String formatDate = date.replace("T", " ");
        return DateUtils.parseDate(formatDate, new String[]{"yyyy-MM-dd HH:mm"});
    }
}