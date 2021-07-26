package com.mercadolibre.calculadorametroscuadrados.unit.services;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.NotFoundLocation;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Repository - Unit Test")
public class CalculateServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private CalculateServiceImpl calculateServices;

    @Test
    @DisplayName("Test: Validar correcto calculo metros cuadrados de la Propiedad")
    void calculateSuccessMeterOK() throws NotFoundLocation {
        //Arrange
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor",20d,10d,null),
                new RoomDTO("Living",20d,30d,null));
        HouseDTO houseDTO = new HouseDTO("House","Palermo",400d,environments);
        Double squareFeetExpected = 800d;

        when(priceRepository.findLocation("Palermo"))
                .thenReturn(new PriceDTO("Palermo"));
        //Act
        HouseResponseDTO houseResponseDTO = calculateServices.calculate(houseDTO);

        //assertion
        verify(priceRepository, Mockito.atLeastOnce()).findLocation("Palermo");
        assertEquals(squareFeetExpected, houseResponseDTO.getSquareFeet());
    }


    @Test
    @DisplayName("Test: Validar exception cuando el districto no existe")
    void calculateAndThrowExceptionNotFoundLocation() throws NotFoundLocation {
        //Arrange
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor",20d,10d,null),
                new RoomDTO("Living",20d,30d,null));
        HouseDTO houseDTO = new HouseDTO("House","San Antonio de Areco",400d,environments);


        when(priceRepository.findLocation(houseDTO.getDistrictName()))
                .thenThrow(NotFoundLocation.class);

        //assertion
        assertThrows(NotFoundLocation.class, ()-> calculateServices.calculate(houseDTO));
        verify(priceRepository, Mockito.atLeastOnce()).findLocation("San Antonio de Areco");

    }

    @Test
    @DisplayName("Test: Validar correcto calculo habitacion más grande de la Propiedad")
    void calculateSuccessBiggerRoom() throws NotFoundLocation {
        //Arrange
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor",20d,10d,null),
                new RoomDTO("Living",20d,30d,null));
        HouseDTO houseDTO = new HouseDTO("House","Palermo",400d,environments);
        RoomDTO biggerRoomExpected = environments.get(1);

        when(priceRepository.findLocation("Palermo"))
                .thenReturn(new PriceDTO("Palermo"));
        //Act
        HouseResponseDTO houseResponseDTO = calculateServices.calculate(houseDTO);

        //assertion
        verify(priceRepository, Mockito.atLeastOnce()).findLocation("Palermo");
        assertSame(biggerRoomExpected,houseResponseDTO.getBiggest());
    }

    @Test
    @DisplayName("Test: Validar correcto calculo metros de cada habitación de la Propiedad")
    void calculateSuccessMeterPeerRoom() throws NotFoundLocation {
        //Arrange
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor",20d,10d,null),
                new RoomDTO("Living",20d,30d,null));
        HouseDTO houseDTO = new HouseDTO("House","Palermo",400d,environments);

        when(priceRepository.findLocation("Palermo"))
                .thenReturn(new PriceDTO("Palermo"));
        //Act
        HouseResponseDTO houseResponseDTO = calculateServices.calculate(houseDTO);

        //assertion
        verify(priceRepository, Mockito.atLeastOnce()).findLocation("Palermo");
        assertAll(()->{
            assertEquals(200, houseResponseDTO.getEnvironment().get(0).getSquareFeet());
            assertEquals(600, houseResponseDTO.getEnvironment().get(1).getSquareFeet());
        });
    }

    @Test
    @DisplayName("Test: Validar metros cuadrados igual a cero, si no posee habitaciones")
    void calculateHouseWhitOutRooms() throws NotFoundLocation {
        //Arrange
        List<RoomDTO> environments = new ArrayList<>();
        HouseDTO houseDTO = new HouseDTO("House","Palermo",400d,environments);

        when(priceRepository.findLocation("Palermo"))
                .thenReturn(new PriceDTO("Palermo"));
        //Act
        HouseResponseDTO houseResponseDTO = calculateServices.calculate(houseDTO);

        //assertion
        verify(priceRepository, Mockito.atLeastOnce()).findLocation("Palermo");
        assertEquals(0d, houseResponseDTO.getSquareFeet());
        assertSame(null,houseResponseDTO.getBiggest());
    }

}
