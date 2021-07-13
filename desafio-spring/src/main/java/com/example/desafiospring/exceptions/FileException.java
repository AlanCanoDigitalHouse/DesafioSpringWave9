package com.example.desafiospring.exceptions;

public class FileException extends Exception {
    public static final String FILE_NOT_FOUND = "File not found at location ";


    public FileException(String message) {
        super(message);
    }
}
