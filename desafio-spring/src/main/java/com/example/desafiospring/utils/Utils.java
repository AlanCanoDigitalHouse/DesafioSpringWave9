package com.example.desafiospring.utils;

import com.example.desafiospring.enums.ConstantEnum;
import com.example.desafiospring.exceptions.DateInvalidException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Utils {

    public static void validateDate(String date) throws DateInvalidException {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern(ConstantEnum.DATE_FORMAT)
                .withResolverStyle(ResolverStyle.SMART);
        try {
            LocalDate parse = LocalDate.parse(date, dt);
            LocalDate now = LocalDate.now();
            if (parse.isAfter(now))
                throw new DateInvalidException("La fecha " + date + " no puede ser mayor a la fecha actual");
        } catch (DateTimeParseException e) {
            throw new DateInvalidException("La fecha " + date + " no es valida");
        }
    }

    public static boolean dateInRange(String date) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern(ConstantEnum.DATE_FORMAT);
        try {
            LocalDate dateIn = LocalDate.parse(date, dt);
            LocalDate dateNow = LocalDate.now().minusDays(14);
            return dateIn.compareTo(dateNow) >= 0;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static int compareDates(String date, String date2, boolean asc) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern(ConstantEnum.DATE_FORMAT);
        LocalDate dateOne = LocalDate.parse(date, dt);
        LocalDate dateTwo = LocalDate.parse(date2, dt);
        return asc ? dateTwo.compareTo(dateOne) : dateOne.compareTo(dateTwo);
    }

    public static int compareNames(String name1, String name2, boolean asc) {
        return asc ? name1.compareTo(name2) : name2.compareTo(name1);
    }

}
