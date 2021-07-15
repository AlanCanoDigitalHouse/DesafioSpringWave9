package com.jbianchini.meli.socialmeli.exception.handler;

import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for Service layer exception handling purposes.
 */
@ControllerAdvice(annotations = RestController.class)
public class ServiceExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(ApplicationException exception) {
        Map<String, String> details = new HashMap<>();
        details.put("Detail", exception.getDetails());

        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), details);
    }
}
