package com.meli.desafiospring.exceptions.custom;

import lombok.Getter;

@Getter
public class BadDateFormat extends RuntimeException{

    String date;
    public BadDateFormat(String date) {
        super("Date format should be dd-mm--yyyy");
        this.date = date;
    }

    public String getDateMessage() {
        return "WRONG DATE: " + date;
    }

}
