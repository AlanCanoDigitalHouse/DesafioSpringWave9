package com.meli.tucasita.controller;

import com.meli.tucasita.dto.ResponseErrorDTO;
import com.meli.tucasita.exception.DataBaseException;
import com.meli.tucasita.exception.DiferentDistrictPriceException;
import com.meli.tucasita.exception.NoDistrictFoundException;
import com.meli.tucasita.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(DataBaseException .class)
    public ResponseEntity<ResponseErrorDTO> DataBaseExceptionHandler(DataBaseException ex) {
        LOGGER.info("No se pudo cargar la base de datos.");
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDistrictFoundException.class)
    public ResponseEntity<ResponseErrorDTO> NoDistrictFoundExceptionHandler(NoDistrictFoundException ex) {
        LOGGER.info("No se pudo encontrar el distrito en la base de datos.");
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDTO> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        LOGGER.info("Valores de entrada incorrectos");
        return new ResponseEntity<>(new ResponseErrorDTO(Constant.METHOD_ARGUMENTNOTVALID_EXCEPTION,ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DiferentDistrictPriceException.class)
    public ResponseEntity<ResponseErrorDTO> DiferentDistrictPriceExceptionHandler(DiferentDistrictPriceException ex) {
        LOGGER.info("El precio del distrito no coincide con el precio en base de datos");
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }
}
