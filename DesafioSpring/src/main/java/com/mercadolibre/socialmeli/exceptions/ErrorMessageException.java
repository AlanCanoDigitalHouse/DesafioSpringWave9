package com.mercadolibre.socialmeli.exceptions;

import lombok.Getter;

@Getter
public class ErrorMessageException {
    private Integer status;
    private String message;

    public ErrorMessageException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }


}
