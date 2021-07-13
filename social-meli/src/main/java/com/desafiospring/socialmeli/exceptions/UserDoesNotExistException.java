package com.desafiospring.socialmeli.exceptions;

public class UserDoesNotExistException extends UserException {

    public UserDoesNotExistException(int userId, boolean seller) {
        super();
        if (seller) {
            this.ERROR = "No existe el vendedor con id: " + userId;
        } else {
            this.ERROR = "No existe el usuario con id: " + userId;
        }
    }
}
