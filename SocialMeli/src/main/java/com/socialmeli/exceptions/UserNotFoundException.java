package com.socialmeli.exceptions;

public class UserNotFoundException extends NotFoundException{

    public UserNotFoundException() {
        this.ERROR = "User not found in Registers";
    }
}
