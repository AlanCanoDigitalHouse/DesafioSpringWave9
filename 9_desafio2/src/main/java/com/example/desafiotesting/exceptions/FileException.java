package com.example.desafiotesting.exceptions;

public class FileException extends Exception {
    public static final String CANT_DECODE_FILE = "Can't decode file content";
    public static final String FILE_NOT_FOUND = "File not found";

    public FileException(String message) {
        super(message);
    }
}
