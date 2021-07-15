package com.jbianchini.meli.socialmeli.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static LocalDate convertToLocalDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return LocalDate.parse(date, formatter);
    }

    public static String convertToString(LocalDate date, String format){
        return date.format(DateTimeFormatter.ofPattern(format));
    }
}
