package com.socialMeli.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

//https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
public class DateValidatorDateTimeFormatter {

    public static boolean isValid(final String date) throws DateTimeParseException {
        boolean valid;
        try {
            // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
            //noinspection SpellCheckingInspection
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("dd-MM-uuuu")
                            .withResolverStyle(ResolverStyle.STRICT)
            );
            valid = true;
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return valid;
    }
}
