package com.example.demo.Exceptions;
import lombok.Data;

@Data
public class ExceptionHandler extends Exception {

    /*
    private ErrorDTO error;
    private HttpStatus status;

    public ExceptionHandler(String message, HttpStatus status){
        this.error = new ErrorDTO();
        this.error.setMessage(message);
        this.error.setName(this.getClass().getSimpleName());

        this.status = status;

    }*/

    public static String ERROR;
    public ExceptionHandler() {
        super();
    }

}
