package com.mercadolibre.desafio_testing.exception;

import com.mercadolibre.desafio_testing.util.ConstantsUtils;

public class DuplicateRoomException extends GenericCustomException {
    public DuplicateRoomException() {
        super(ConstantsUtils.ROOM_ALREADY_EXISTS);
    }
}
