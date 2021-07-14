package com.socialMeli.exception;

import com.socialMeli.SocialMeliApplication;
import com.socialMeli.exception.exception.*;
import com.socialMeli.exception.model.ErrorAttributes;
import com.socialMeli.exception.model.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.ParseException;
import java.util.List;

@SuppressWarnings({"ALL", "unused"})
@ControllerAdvice
public class ApiException {
    final Logger logger = LoggerFactory.getLogger(SocialMeliApplication.class);

    // Custom exceptions
    @SuppressWarnings("unused")
    @ExceptionHandler(EmptyModelList.class)
    public ResponseEntity<ErrorMessage> emptyModelList(EmptyModelList ex) {
        return new ResponseEntity<>(new ErrorMessage("Model not found", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @SuppressWarnings("unused")
    @ExceptionHandler(ModelAlreadyExists.class)
    public ResponseEntity<ErrorMessage> emptyModelList(ModelAlreadyExists ex) {
        return new ResponseEntity<>(new ErrorMessage("Model already exists", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @SuppressWarnings("unused")
    @ExceptionHandler(ModelNotExists.class)
    public ResponseEntity<ErrorMessage> emptyModelList(ModelNotExists ex) {
        return new ResponseEntity<>(new ErrorMessage("Model not found", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(FollowHimselfException.class)
    public ResponseEntity<ErrorMessage> followHimself(FollowHimselfException ex) {
        return new ResponseEntity<>(new ErrorMessage("User cant follow himself", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(AlreadyFollowedException.class)
    public ResponseEntity<ErrorMessage> alreadyFollowed(AlreadyFollowedException ex) {
        return new ResponseEntity<>(new ErrorMessage("User already following this user", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDontFollowThisUser.class)
    public ResponseEntity<ErrorMessage> userDontFollowThisUser(UserDontFollowThisUser ex) {
        return new ResponseEntity<>(new ErrorMessage("User don't follow the other user", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(OrderNotValidException.class)
    public ResponseEntity<ErrorMessage> orderNotValid(OrderNotValidException ex){
        return new ResponseEntity<>(new ErrorMessage("Order is invalid", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    //Default exceptions

    @SuppressWarnings("unused")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> httpMessageBodyNotValid(HttpMessageNotReadableException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorMessage("Error in the body", "A value of the body is unexpected, a integer param has been passed as a string?"), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorAttributes> argumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorAttributes errorAttributes = new ErrorAttributes("Error with a few attributes");
        fieldErrors.forEach(fieldError -> errorAttributes.addFieldError(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> failedTypeEndpoint(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new ErrorMessage("Type error", "A type passed in the endpoint was unexpected, you enter a letter and not a number?"), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorMessage> parseException(ParseException ex) {
        return new ResponseEntity<>(new ErrorMessage("Error parsing", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(DateNotValidException.class)
    public ResponseEntity<ErrorMessage> dateNotValidException(DateNotValidException ex) {
        return new ResponseEntity<>(new ErrorMessage("Error parsing date", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> emptyModelList(Exception ex) {
        ex.printStackTrace();
        logger.error("Exception without control generated");
        return new ResponseEntity<>(new ErrorMessage("Exception", "unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
