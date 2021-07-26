package com.mercadolibre.socialmeli.exception;

import javax.management.RuntimeMBeanException;

public class OrderException extends RuntimeException {
    public OrderException() {
        super();
    }
    public OrderException(String message) {
        super(message);
    }

}
