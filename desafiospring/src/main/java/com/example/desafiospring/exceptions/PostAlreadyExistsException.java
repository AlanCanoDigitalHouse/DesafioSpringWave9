package com.example.desafiospring.exceptions;

public class PostAlreadyExistsException extends Exception{

    public final String ERROR = "Post already exists or same postID";

    public PostAlreadyExistsException(){ super();}

}
