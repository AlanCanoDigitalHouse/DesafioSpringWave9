package com.socialMeli.exception;

import com.socialMeli.SocialMeliApplication;
import com.socialMeli.exception.exception.*;
import com.socialMeli.exception.model.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiException {
    final Logger logger = LoggerFactory.getLogger(SocialMeliApplication.class);

    // Custom exceptions
    @ExceptionHandler(EmptyModelList.class)
    public ResponseEntity<ErrorMessage> emptyModelList(EmptyModelList ex) {
        return new ResponseEntity<>(new ErrorMessage("Model not found", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ModelAlreadyExists.class)
    public ResponseEntity<ErrorMessage> emptyModelList(ModelAlreadyExists ex) {
        return new ResponseEntity<>(new ErrorMessage("Model already exists", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ModelNotExists.class)
    public ResponseEntity<ErrorMessage> emptyModelList(ModelNotExists ex) {
        return new ResponseEntity<>(new ErrorMessage("Model not found", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FollowHimselfException.class)
    public ResponseEntity<ErrorMessage> followHimself(FollowHimselfException ex) {
        return new ResponseEntity<>(new ErrorMessage("User cant follow himself", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyFollowedException.class)
    public ResponseEntity<ErrorMessage> alreadyFollowed(AlreadyFollowedException ex) {
        return new ResponseEntity<>(new ErrorMessage("User already following this user", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    //Default exceptions
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorMessage> numberFormatException(NumberFormatException ex) {
        return new ResponseEntity<>(new ErrorMessage("Bad entry", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> emptyModelList(Exception ex) {
        ex.printStackTrace();
        logger.error("Exception without control generated");
        return new ResponseEntity<>(new ErrorMessage("Exception", "unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
