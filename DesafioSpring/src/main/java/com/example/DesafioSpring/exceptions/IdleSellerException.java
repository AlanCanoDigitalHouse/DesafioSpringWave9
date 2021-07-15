package com.example.DesafioSpring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IdleSellerException extends RuntimeException {

    public IdleSellerException(String s) {
        super(s);
    }

}
