package com.bootcamp.desafio2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessage extends Exception {

    public ErrorMessage(String message) {
        super(message);
    }
}
