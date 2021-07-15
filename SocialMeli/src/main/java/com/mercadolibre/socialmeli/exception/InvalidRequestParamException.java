package com.mercadolibre.socialmeli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class InvalidRequestParamException extends ResponseStatusException {
    public InvalidRequestParamException(String parameter, List<String> validParameters){
        super(HttpStatus.NOT_ACCEPTABLE, "Invalid parameter value: " + parameter + ". Valid options: " + validParameters.toString());
    }
}
