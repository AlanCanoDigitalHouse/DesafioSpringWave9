package com.meli.joescaos.socialmeli.socialmeli.exceptions;

public class UserAlreadyFollowedException extends Exception{
    public final String ERROR = "Ya sigues a este usuario";

    public UserAlreadyFollowedException(){ super(); }

}
