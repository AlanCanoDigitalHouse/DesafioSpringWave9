package com.socialmeli.exceptions.Found;

public class PostNotFoundException extends NotFoundException {

    public PostNotFoundException() {
        ERROR = "Post not found in Registers";
    }
}
