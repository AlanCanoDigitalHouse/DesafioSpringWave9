package com.mercadolibre.desafiospring.util;

import com.mercadolibre.desafiospring.exception.post.InvalidPostException;
import com.mercadolibre.desafiospring.model.Post;
import com.mercadolibre.desafiospring.model.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String DATE_FORMAT_STR = "dd-M-yyyy";
    public static final SimpleDateFormat dateFormat =
            new SimpleDateFormat(DATE_FORMAT_STR);

    public static Date parseDate(String date)
            throws InvalidPostException {
        try {
            dateFormat.setLenient(false);
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new InvalidPostException(
                    "Post date must be valid and have the format: dd-mm-yyyy");
        }
    }

}
