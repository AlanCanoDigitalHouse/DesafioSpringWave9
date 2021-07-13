package com.socialMeli.exception.exception;

public class ModelAlreadyExists extends Exception {
    public ModelAlreadyExists(String idModel) {
        super("The model with id: " + idModel + " already exists");
    }
}
