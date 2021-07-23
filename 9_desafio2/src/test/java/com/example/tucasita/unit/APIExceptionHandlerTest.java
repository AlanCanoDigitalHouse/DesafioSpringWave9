package com.example.tucasita.unit;

import com.example.tucasita.exceptions.APIExceptionHandler;
import com.example.tucasita.exceptions.ErrorMessage;
import com.example.tucasita.exceptions.general.DBNotAvailableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class APIExceptionHandlerTest {

    APIExceptionHandler apiExceptionHandler =new APIExceptionHandler();

    @Test
    void handleException(){
        //arrange
        String message = "Mensaje de error";

        //act
        ErrorMessage em= apiExceptionHandler.handleGeneralException(new DBNotAvailableException(message,new Exception()));

        //assert
        assertEquals(message, em.getErrorMessage());
    }

}