package com.mercadolibre.desafio_testing.exception;

import com.mercadolibre.desafio_testing.util.ConstantsUtils;

public class PropertyDoesNotExistsException extends GenericCustomException {
    public PropertyDoesNotExistsException() {
        super(ConstantsUtils.UNEXISTING_PROPERTY);
    }
}
