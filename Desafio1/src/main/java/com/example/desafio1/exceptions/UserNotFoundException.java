package com.example.desafio1.exceptions;

import javax.validation.ConstraintDeclarationException;

public class UserNotFoundException extends ConstraintDeclarationException {
    private final Integer userId;

    public UserNotFoundException(Integer userId) {
        this.userId = userId;
    }

    public String getMessage(){
        return String.format("User with Id %d does not exist", this.userId);
    }
}
