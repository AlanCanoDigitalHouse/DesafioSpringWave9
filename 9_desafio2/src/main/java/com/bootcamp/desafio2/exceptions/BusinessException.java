package com.bootcamp.desafio2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }
}
