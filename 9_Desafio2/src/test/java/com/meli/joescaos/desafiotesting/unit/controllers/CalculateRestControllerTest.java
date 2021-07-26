package com.meli.joescaos.desafiotesting.unit.controllers;

import com.meli.joescaos.desafiotesting.controllers.CalculateRestController;
import com.meli.joescaos.desafiotesting.dto.HouseDTO;
import com.meli.joescaos.desafiotesting.dto.HouseResponseDTO;
import com.meli.joescaos.desafiotesting.services.CalculateService;
import com.meli.joescaos.desafiotesting.utilsTest.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@DisplayName("RestController Test")
public class CalculateRestControllerTest {

    @Mock
    CalculateService calculateService;

    @InjectMocks
    CalculateRestController calculateRestController;


    @Test
    @DisplayName("Response calculation Test")
    public void responseCalculationTest(){
        HouseDTO payload = TestUtilsGenerator.createHouse();
        HouseResponseDTO responseDto = TestUtilsGenerator.createHouseResponse();
        Mockito.when(calculateService.calculate(payload)).thenReturn(responseDto);
        ResponseEntity<HouseResponseDTO> response = calculateRestController.calculate(payload);
        Mockito.verify(calculateService, Mockito.atLeastOnce()).calculate(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
