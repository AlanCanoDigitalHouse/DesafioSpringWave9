package com.mercado_libre.bootcamp.spring.desafio.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of("UTC"))
                .toLocalDate();
    }

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }
}
