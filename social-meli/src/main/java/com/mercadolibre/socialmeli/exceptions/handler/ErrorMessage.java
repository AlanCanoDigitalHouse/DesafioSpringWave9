package com.mercadolibre.socialmeli.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private Integer status;
    private String error;
    private Map<String, String> errors;

    public ErrorMessage(Integer status, String error, Map<String, String> errors) {
        this.status = status;
        this.error = error;
        this.errors = errors;
    }

}
