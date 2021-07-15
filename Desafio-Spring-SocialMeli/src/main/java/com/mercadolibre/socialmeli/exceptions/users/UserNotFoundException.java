package com.mercadolibre.socialmeli.exceptions.users;

public class UserNotFoundException extends Exception {

    private Integer userId;

    public UserNotFoundException(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return "No user with ID '" + userId.toString() + "' was found";
    }
}
