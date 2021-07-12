package com.example.desafiospring.exceptions;

import com.example.desafiospring.exceptions.general.DBNotAvailableException;
import com.example.desafiospring.exceptions.general.NoSuchElementInDBException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(NoSuchElementInDBException exception) {
        return handleGeneralException(exception);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(DBNotAvailableException exception) {
        return handleGeneralException(exception);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleGeneralException(Exception exception){
        exception.printStackTrace();
        String message = exception.getMessage();
        Map<String,String> details = getDetails (exception);
        return new ErrorMessage(message, details);
    }

    private Map<String, String> getDetails(Throwable exception) {
        Map<String, String> details = new HashMap<>();
        Throwable cause = exception.getCause();
        if (cause!=null &&cause!=exception) {
            details.put("CAUSE", cause.toString());
            details.put("STACK_TRACE", stackTraceToString(cause.getStackTrace()));
            details.putAll(getDetails(cause));
        }
        return details;
    }

    private String stackTraceToString(StackTraceElement[] stackTrace) {
        StringBuilder stackTraceAsString= new StringBuilder();
        for (StackTraceElement element:
             stackTrace) {
            stackTraceAsString.append(element.toString()).append(System.lineSeparator());
        }
        return stackTraceAsString.toString();
    }


}
