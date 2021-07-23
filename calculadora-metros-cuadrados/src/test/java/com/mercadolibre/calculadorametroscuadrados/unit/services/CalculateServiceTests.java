package com.mercadolibre.calculadorametroscuadrados.unit.services;

import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.LocationRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.utils.HouseRequestInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTests {

    @Mock
    LocationRepository repo;

    @InjectMocks
    CalculateService service;

    @Test
    void validPayloadReturnsHttpResponse200OK() {
        // arrange
        HouseRequestDTO house = HouseRequestInitializer.house();
        // act
        ResponseEntity<HouseResponseDTO> houseResponse = service.allInOneCalculator(house);
        // assert
        assertEquals(houseResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void validPayloadReturnsExpectedTotalPriceAndAreas() {
        // arrange
        HouseRequestDTO house = HouseRequestInitializer.house();
        HouseResponseDTO expectedHouseResponse = new HouseResponseDTO(
                house.getProp_name(),
                house.calculateHouseArea(),
                house.calculateHouseArea() * house.getDistrict_price(),
                house.getBiggestRoom().getEnvironment_name(),
                house.getRoomAreasDTOs()
        );
        // act
        ResponseEntity<HouseResponseDTO> houseResponse = service.allInOneCalculator(house);
        // assert
        assertEquals(houseResponse, expectedHouseResponse);
    }

    @Test
    void repositoryChecksIfLocationExists() {
        // arrange
        HouseRequestDTO house = HouseRequestInitializer.house();
        // act
        ResponseEntity<HouseResponseDTO> houseResponse = service.allInOneCalculator(house);
        // assert
        verify(repo, atLeastOnce()).locationExists(house.getDistrict_name());
    }



}
