package com.jbianchini.meli.socialmeli.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date formatting purposes.
 */
public class DateUtils {

    /** Converts string date to Local Date
     * @param date String with date
     * @param format The date format
     * @return a {@link LocalDate}
     */
    public static LocalDate convertToLocalDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return LocalDate.parse(date, formatter);
    }

    /** Converts a Local Date to String
     * @param date LocalDate to convert
     * @param format Desired format
     * @return String with date
     */
    public static String convertToString(LocalDate date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }
}
