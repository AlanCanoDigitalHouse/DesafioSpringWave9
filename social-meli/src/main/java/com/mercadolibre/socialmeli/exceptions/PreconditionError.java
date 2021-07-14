package com.mercadolibre.socialmeli.exceptions;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class PreconditionError extends Exception {
    public final String ERROR = "The discount field must not be 0";

    public PreconditionError() {
        super();
    }
}