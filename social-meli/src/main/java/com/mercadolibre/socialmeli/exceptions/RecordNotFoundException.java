package com.mercadolibre.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RecordNotFoundException extends ResponseStatusException {

    public RecordNotFoundException(Class clazz, Object id) {
        super(HttpStatus.BAD_REQUEST, clazz.getSimpleName() + " not found: " + id);
    }

}
