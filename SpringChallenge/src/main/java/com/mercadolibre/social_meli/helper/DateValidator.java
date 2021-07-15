package com.mercadolibre.social_meli.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public static boolean isValid(String dateString) {
        try {
            LocalDate.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }

}
