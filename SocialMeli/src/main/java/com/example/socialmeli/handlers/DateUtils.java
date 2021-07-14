package com.example.socialmeli.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtils {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static Date stringToDate(String date) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(date);
    }

    public static String dateToString(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static Date getDateTwoWeekBefore() {
        LocalDate now = LocalDate.now();
        return java.sql.Date.valueOf(now.minusWeeks(2));
    }
}
