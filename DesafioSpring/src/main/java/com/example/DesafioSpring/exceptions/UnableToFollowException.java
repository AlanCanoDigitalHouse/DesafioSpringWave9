package com.example.DesafioSpring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnableToFollowException extends RuntimeException{

    public UnableToFollowException(String message){
        super(message);
    }

}
