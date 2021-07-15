package desafiospringw9.demo.controllers;

import desafiospringw9.demo.dtos.ErrorDTO;
import desafiospringw9.demo.exceptions.MeliException;
import desafiospringw9.demo.exceptions.PostIdNotValidExceptions;
import desafiospringw9.demo.exceptions.ProductIdNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionsController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(MeliException e){
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

    @ExceptionHandler(PostIdNotValidExceptions.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(PostIdNotValidExceptions e){
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

    @ExceptionHandler(ProductIdNotValidException.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(ProductIdNotValidException e){
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

}
