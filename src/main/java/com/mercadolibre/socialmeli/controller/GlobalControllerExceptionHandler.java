package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.exception.ExistFollowException;
import com.mercadolibre.socialmeli.exception.ExistsUserException;
import com.mercadolibre.socialmeli.exception.FollowException;
import com.mercadolibre.socialmeli.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ExistsUserException.class)
    public ResponseEntity handleInvalidUrlException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Message", "Invalid UserId"));
    }

    @ExceptionHandler(FollowException.class)
    public ResponseEntity handleCreateFollowException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Message", "Status Code 400 Jack"));
    }

    @ExceptionHandler(ExistFollowException.class)
    public ResponseEntity handleExistsCreateFollowException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Message", "the follow already exists"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleNullPointerException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Message", ex.getMessage()));
    }
}
