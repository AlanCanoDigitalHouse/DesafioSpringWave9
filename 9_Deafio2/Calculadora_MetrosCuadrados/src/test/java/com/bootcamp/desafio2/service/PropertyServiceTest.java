package com.bootcamp.desafio2.service;

import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.dtos.request.EnvironmentDTO;
import com.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;
import com.bootcamp.desafio2.services.IDistrictService;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @InjectMocks
    PropertyService propertyService;

    @Mock
    IDistrictService districtService;



    @Test
    @DisplayName("Validación propiedad - Habitación mas grande.")
    void getBiggetsRoomTest() throws DistrictNotExistsException, PriceNotMatchException {

        HouseDTO houseDTO = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO expectedRoom = houseDTO.getRooms().stream().max(Comparator.comparingDouble(r -> r.getSquareMeters())).get();

        when(districtService.findPriceByLocation("Belgrano", 1100D)).thenReturn(1100D);

        HouseResponseDTO response = propertyService.calculateArea(houseDTO);
        EnvironmentDTO current = response.getBiggestRoom();

        assertAll(() -> assertEquals(expectedRoom.getEnvironment_name(), current.getEnvironment_name()),
                () -> assertEquals(expectedRoom.getEnvironment_length(), current.getEnvironment_length()),
                () -> assertEquals(expectedRoom.getEnvironment_width(), current.getEnvironment_width()));

    }

    @Test
    @DisplayName("Validación propiedad - calculo de precio de la propiedad.")
    void calculatePriceTest() throws DistrictNotExistsException, PriceNotMatchException {

        HouseDTO houseDTO = TestUtilsGenerator.getDefaultHouse();
        HouseResponseDTO responseDTO = TestUtilsGenerator.getDefaultHouseResponse();

        when(districtService.findPriceByLocation("Belgrano", 1100D)).thenReturn(1100D);

        HouseResponseDTO response = propertyService.calculateArea(houseDTO);
        double current = response.getHousePrice();

        assertEquals(responseDTO.getHousePrice(), current);

    }

    @Test
    @DisplayName("Validación propiedad - calculo de metros cuadrados de la propiedad.")
    void calculateTotalAreaTest() throws DistrictNotExistsException, PriceNotMatchException {

        HouseDTO houseDTO = TestUtilsGenerator.getDefaultHouse();

        when(districtService.findPriceByLocation("Belgrano", 1100D)).thenReturn(1100D);

        HouseResponseDTO response = propertyService.calculateArea(houseDTO);
        double current = response.getTotalArea();

        assertEquals(61, current);

    }


}
