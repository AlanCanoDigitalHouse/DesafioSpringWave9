package com.mercadolibre.socialmedia.exceptions;

import lombok.Data;

@Data
public class RequestParamException extends RuntimeException{
    private String msg;
    public RequestParamException(String msg){
        super();
        this.msg = msg;
    }
}
