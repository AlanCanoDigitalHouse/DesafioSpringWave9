package com.example.desafio1.exceptions;

import javax.validation.ConstraintDeclarationException;

public class UserAlreadyFollowingException extends ConstraintDeclarationException {
    private final Integer userId;
    private final Integer userIdToFollow;

    public UserAlreadyFollowingException(Integer userId, Integer userIdToFollow) {
        this.userId = userId;
        this.userIdToFollow = userIdToFollow;
    }

    public String getMessage(){
        return String.format("User with Id %d already follows %d", this.userId, this.userIdToFollow);
    }
}
