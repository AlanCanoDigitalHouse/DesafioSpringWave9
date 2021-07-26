package com.mercadolibre.calculadorametroscuadrados.unit.exceptions;

import com.mercadolibre.calculadorametroscuadrados.dto.response.ResponseErrorDto;
import com.mercadolibre.calculadorametroscuadrados.exceptions.ApiExceptionsControllerAdvice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectLocationPrice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
public class ExceptionTest {


    @Test
    void locationNotFoundExceptionTest(){
        ApiExceptionsControllerAdvice apiExceptionsControllerAdvice = new ApiExceptionsControllerAdvice();

        LocationNotFound locationNotFound = new LocationNotFound();
        ResponseErrorDto expectedErrorMessage =
                new ResponseErrorDto(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                        locationNotFound.getMessage());
        ResponseEntity expectedErrorResponse = new ResponseEntity<>(expectedErrorMessage, HttpStatus.NOT_FOUND);

        ResponseEntity<ResponseErrorDto> errorMessage = apiExceptionsControllerAdvice.locationNotFoundExceptionHandler(locationNotFound);

        Assertions.assertEquals(expectedErrorResponse,errorMessage);
    }

    @Test
    void incorrectLocationPriceExceptionTest(){
        ApiExceptionsControllerAdvice apiExceptionsControllerAdvice = new ApiExceptionsControllerAdvice();

        IncorrectLocationPrice locationNotFound = new IncorrectLocationPrice();
        ResponseErrorDto expectedErrorMessage =
                new ResponseErrorDto(HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        locationNotFound.getMessage());
        ResponseEntity expectedErrorResponse = new ResponseEntity<>(expectedErrorMessage, HttpStatus.NOT_FOUND);

        ResponseEntity<ResponseErrorDto> errorMessage = apiExceptionsControllerAdvice.incorrectLocationPriceExceptionHandler(locationNotFound);

        Assertions.assertEquals(expectedErrorResponse,errorMessage);
    }
}
