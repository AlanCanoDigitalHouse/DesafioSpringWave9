package com.mercadolibre.socialmeli.exceptions;

import lombok.Data;

@Data
public class UserBadRequest extends Exception{
    public final String ERROR= "User not exist";

    public UserBadRequest() {
        super();
    }
}
