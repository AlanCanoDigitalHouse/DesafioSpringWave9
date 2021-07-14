package com.mercadolibre.desafio.utils;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {

    public static Long weeksBetween(Date date1, Date date2) {
        long days = ChronoUnit.DAYS.between(date1.toInstant(), date2.toInstant());
        return days / 7 + (days % 7 == 0 ? 0 : 1);
    }

}
