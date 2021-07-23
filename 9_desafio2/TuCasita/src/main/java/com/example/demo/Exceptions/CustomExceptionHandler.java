package com.example.demo.Exceptions;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CustomExceptionHandler extends Exception {

    public static String ERROR;
    public CustomExceptionHandler() {
        super();
    }

}
