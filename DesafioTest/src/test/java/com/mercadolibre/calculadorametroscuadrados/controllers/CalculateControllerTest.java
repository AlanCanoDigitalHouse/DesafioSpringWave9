package com.mercadolibre.calculadorametroscuadrados.controllers;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.ErrorDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepositoryImpl;
import com.mercadolibre.calculadorametroscuadrados.services.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.utils.TestUtilGenerator;
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


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateControllerTest {

    @Mock
    CalculateService service;

    @InjectMocks
    CalculateRestController controller;


    @Test
    @DisplayName("Test Calculate")
    public void calculateHouseProperties() {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        HouseDTO expected = TestUtilGenerator.getHouseToResponseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();

        // act
        HouseDTO responseFromService = TestUtilGenerator.getHouseToResponseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        when(service.calculate(request)).thenReturn(responseFromService);

        //act
        ResponseEntity<HouseDTO> current = controller.calculate(request);

        //assert
        Mockito.verify(service, Mockito.atLeast(1)).calculate(Mockito.any(HouseDTO.class));
        Assertions.assertEquals(current.getBody(), expected);
        Assertions.assertEquals(current.getStatusCode(), HttpStatus.OK);

    }


}
