package com.socialMeli.exception.exception;

public class EmptyModelList extends Exception {
    public EmptyModelList() {
        super("The list passed is empty");
    }
}
