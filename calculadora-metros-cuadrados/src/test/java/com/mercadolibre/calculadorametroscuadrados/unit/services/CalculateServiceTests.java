package com.mercadolibre.calculadorametroscuadrados.unit.services;

import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.LocationRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.utils.HouseRequestInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTests {

    @Mock
    LocationRepository repo;

    @InjectMocks
    CalculateService service;

    @BeforeEach
    void setUpMock() {
        when(repo.locationExists("Palermo")).thenReturn(true);
    }

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
                house.calculateBiggestRoom().getEnvironment_name(),
                house.calculateRoomAreasDTOs()
        );
        // act
        ResponseEntity<HouseResponseDTO> houseResponse = service.allInOneCalculator(house);
        // assert
        assertEquals(houseResponse.getBody(), expectedHouseResponse);
    }

    /** (PUNTO 2) Si existe la location en la base de datos (locations.json), permite continuar con normalidad.*/
    @Test
    void repositoryChecksIfLocationExistsAndIfItExistsContinuesNormally() {
        // arrange
        HouseRequestDTO house = HouseRequestInitializer.house().inDistrict("Palermo");
        // act
        ResponseEntity<HouseResponseDTO> houseResponse = service.allInOneCalculator(house);
        // assert
        verify(repo, atLeastOnce()).locationExists(house.getDistrict_name());
    }

    /** (PUNTO 2) Si existe la location en la base de datos (locations.json), notifica la no coincidencia
      mediante una excepción.*/

    @Test
    void repositoryChecksIfLocationExistsAndIfItDoesNotExistThrowsException() {
        // arrange
        HouseRequestDTO house = HouseRequestInitializer.house().inDistrict("Purmamarca");
        // act/assert
        assertThrows(RuntimeException.class, () -> service.allInOneCalculator(house));
    }

}
