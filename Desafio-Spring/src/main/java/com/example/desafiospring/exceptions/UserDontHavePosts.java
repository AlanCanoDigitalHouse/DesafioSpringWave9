package com.example.desafiospring.exceptions;

public class UserDontHavePosts extends Exception{

    public final String ERROR_POSTS_LIST = "User dont have posts yes";

    public UserDontHavePosts() {
        super();
    }
}
