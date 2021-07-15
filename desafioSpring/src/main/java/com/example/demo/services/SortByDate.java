package com.example.demo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class SortByDate implements Comparator<com.example.demo.dtos.request.Post> {

    static final DateTimeFormatter DATEFF = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public int compare(com.example.demo.dtos.request.Post o1, com.example.demo.dtos.request.Post o2) {
        /*LocalDate date1 = LocalDate.parse(o1.getDate(), DATEFF);
        LocalDate date2 = LocalDate.parse(o2.getDate(), DATEFF);*/
        return o1.getDate().compareTo(o2.getDate());
    }
}
