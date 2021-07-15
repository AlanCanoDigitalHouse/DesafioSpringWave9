package com.example.desafiospring.exceptions;

public class UserDontHaveFollowersorFollowed extends Exception {

    public final String ERROR_LIST = "This user dont have ";
    public String TYPE = "";

    public UserDontHaveFollowersorFollowed(String type) {
        this.TYPE = type;
    }
}
