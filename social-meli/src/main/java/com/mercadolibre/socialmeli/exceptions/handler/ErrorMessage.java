package com.mercadolibre.socialmeli.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private Integer status;
    private String error;
    private String message;
    private Map<String, String> details;

    public ErrorMessage(Integer status, String error, String message, Map<String, String> details) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
    }

}
