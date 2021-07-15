package com.example.desafiospring.exceptions;

public class UserDontHavePostsException extends Exception{

    public final String ERROR_POSTS_LIST = "User dont have posts yet";

    public UserDontHavePostsException() {
        super();
    }
}
