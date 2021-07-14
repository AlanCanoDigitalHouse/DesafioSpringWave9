package com.socialmeli.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ListErrorMessage extends ErrorMessage{

    private Map<String, String> details;

    public ListErrorMessage(String error, Map<String, String> details) {
        super(error);
        this.details = details;
    }
}
