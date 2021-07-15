package com.kjcelis.social_meli.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage extends Throwable {

    private Integer status;
    private String message;

    public ErrorMessage(String message) {
    }
}