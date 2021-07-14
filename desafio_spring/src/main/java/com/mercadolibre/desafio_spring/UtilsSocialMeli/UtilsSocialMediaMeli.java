package com.mercadolibre.desafio_spring.UtilsSocialMeli;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UtilsSocialMediaMeli {
    public static LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
