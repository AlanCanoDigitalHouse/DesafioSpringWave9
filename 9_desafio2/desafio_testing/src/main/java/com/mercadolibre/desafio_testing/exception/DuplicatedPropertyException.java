package com.mercadolibre.desafio_testing.exception;

import com.mercadolibre.desafio_testing.util.ConstantsUtils;

public class DuplicatedPropertyException extends GenericCustomException {
    public DuplicatedPropertyException() {
        super(ConstantsUtils.PROPERTY_ALREADY_EXISTS);
    }
}
