package com.example.DesafioSpring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnrelatedUsersException extends RuntimeException {

    public UnrelatedUsersException(String message){
        super(message);
    }

}
