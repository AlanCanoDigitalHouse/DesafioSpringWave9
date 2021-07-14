package com.mercadolibre.socialmeli.exceptions;

import lombok.Data;

@Data
public class ExpectationFailed extends Exception{
    public final String ERROR= "The request could not be completed";

    public ExpectationFailed() {
        super();
    }
}
