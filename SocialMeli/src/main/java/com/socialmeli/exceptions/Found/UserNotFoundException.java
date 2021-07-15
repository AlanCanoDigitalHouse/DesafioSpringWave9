package com.socialmeli.exceptions.Found;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        ERROR = "User not found in Registers";
    }
}
