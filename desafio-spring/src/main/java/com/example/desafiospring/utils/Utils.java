package com.example.desafiospring.utils;

import com.example.desafiospring.exceptions.DateInvalidException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    public static void validateDate(String date) throws DateInvalidException {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(date, dt);
        } catch (DateTimeParseException e) {
            throw new DateInvalidException("La fecha " + date + " no es valida");
        }
    }

    public static boolean dateInRange(String date) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate dateIn = LocalDate.parse(date, dt);
            LocalDate dateNow = LocalDate.now().minusDays(14);
            return dateIn.compareTo(dateNow) >= 0;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static int compareDates(String date, String date2, boolean asc) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateOne = LocalDate.parse(date, dt);
        LocalDate dateTwo = LocalDate.parse(date2, dt);
        return asc ? dateOne.compareTo(dateTwo) : dateTwo.compareTo(dateOne);
    }

    public static int compareNames(String name1, String name2, boolean asc) {
        return asc ? name1.compareTo(name2) : name2.compareTo(name1);
    }

}
