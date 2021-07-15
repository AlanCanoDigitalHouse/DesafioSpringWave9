package com.example.demo.exceptions;

public class EmptyFile extends Exception{

    public final String ERROR = "Database is empty, there are not information to show";

    public EmptyFile() {
        super();
    }
}
