package com.mercadolibre.desafio.demo.exceptions.userException.exceptions;

import lombok.Getter;

@Getter
public class RepeatUserIdException extends RuntimeException {
    private final String MESSAGE = "Bad Request.";
    private final Integer sellerId;
    private final Integer buyerId;
    private final String customMessage;

    public RepeatUserIdException(String message, Integer sellerId, Integer buyerId) {
        super(message);
        this.customMessage = message;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
    }

    public String getCustomMessage() {
        return MESSAGE+ " "+customMessage +" (" + sellerId + ", " + buyerId + ").";
    }
}
