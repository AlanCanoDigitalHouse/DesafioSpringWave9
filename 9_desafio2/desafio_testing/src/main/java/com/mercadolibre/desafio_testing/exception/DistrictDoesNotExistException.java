package com.mercadolibre.desafio_testing.exception;

import com.mercadolibre.desafio_testing.util.ConstantsUtils;

public class DistrictDoesNotExistException extends GenericCustomException {
    public DistrictDoesNotExistException() {
        super(ConstantsUtils.UNEXISTING_DISTRICT);
    }
}
