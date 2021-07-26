package com.kjcelis.calculadora_mts.unit.controller;

import com.kjcelis.calculadora_mts.controller.HouseController;
import com.kjcelis.calculadora_mts.dto.DistrictDTO;
import com.kjcelis.calculadora_mts.dto.request.EnvironmentDTO;
import com.kjcelis.calculadora_mts.dto.request.HouseRequestDTO;
import com.kjcelis.calculadora_mts.dto.response.HouseResponseDTO;
import com.kjcelis.calculadora_mts.services.CalculateServiceImpl;
import com.kjcelis.calculadora_mts.util.TestUtilGenerator;
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

@DisplayName("Unit Test - HouseController")
@ExtendWith(MockitoExtension.class)
public class HouseControllerTest {

    @Mock
    CalculateServiceImpl service;


    @InjectMocks
    HouseController controller;


    @DisplayName("Verificar llamado del end point")
    @Test
    void calculeInfoPropTst() {

        Mockito.when(service.calculate(TestUtilGenerator.getRequestDTO())).thenReturn(TestUtilGenerator.getResponseDTO());
        ResponseEntity<HouseResponseDTO> response = controller.calculeInfoProp(TestUtilGenerator.getRequestDTO());
        Mockito.verify(service, Mockito.atLeastOnce()).calculate(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
