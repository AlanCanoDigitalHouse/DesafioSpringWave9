package com.mercadolibre.desafio_testing.exception;

import com.mercadolibre.desafio_testing.util.ConstantsUtils;

public class DistrictAlreadyExistsException extends GenericCustomException {
    public DistrictAlreadyExistsException() {
        super(ConstantsUtils.DISTRICT_ALREADY_EXISTS);
    }
}
