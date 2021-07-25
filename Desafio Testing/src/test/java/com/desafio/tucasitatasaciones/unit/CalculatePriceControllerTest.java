package com.desafio.tucasitatasaciones.unit;

import com.desafio.tucasitatasaciones.controller.CalculatePriceController;
import com.desafio.tucasitatasaciones.dto.PropertyRequestDTO;
import com.desafio.tucasitatasaciones.dto.PropertyResponseDTO;
import com.desafio.tucasitatasaciones.repository.DistrictRepository;
import com.desafio.tucasitatasaciones.service.DistrictService;
import com.desafio.tucasitatasaciones.util.Request;
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
@DisplayName("Testing controller")
public class CalculatePriceControllerTest {
    @Mock
    DistrictService districtService;
    @InjectMocks
    CalculatePriceController calculatePriceController;

    @Test
    @DisplayName("Gets info")
    void checkGetInfo() throws Exception {
        PropertyRequestDTO requestDTO = Request.getRequest();

        Mockito.when(districtService.propertyInfo(requestDTO)).thenReturn(Request.getResponse());

        ResponseEntity<PropertyResponseDTO> response = calculatePriceController.getInfo(requestDTO);

        Mockito.verify(districtService, Mockito.atLeastOnce()).propertyInfo(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
