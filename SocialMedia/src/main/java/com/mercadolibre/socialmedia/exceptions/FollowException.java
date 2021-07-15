package com.mercadolibre.socialmedia.exceptions;

import lombok.Data;

@Data
public class FollowException extends RuntimeException{
    private String msg;
    public FollowException(String msg){
        super();
        this.msg = msg;
    }
}
