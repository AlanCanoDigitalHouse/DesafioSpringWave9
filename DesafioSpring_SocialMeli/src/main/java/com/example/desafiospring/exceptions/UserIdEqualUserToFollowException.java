package com.example.desafiospring.exceptions;

import lombok.Getter;

@Getter
public class UserIdEqualUserToFollowException extends Exception{

    private String message;

    public UserIdEqualUserToFollowException(String pMessage){
        this.message = pMessage;
    }
}
