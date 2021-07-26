package com.mercadolibre.calculadorametroscuadrados.unit.handler;

import com.mercadolibre.calculadorametroscuadrados.dto.requests.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.requests.RoomRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectLocationPrice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;
import com.mercadolibre.calculadorametroscuadrados.handlers.CalculateHandler;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepository;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

@SpringBootTest
public class CalculateHandlerTest {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private CalculateHandler calculateHandler;

    @Test
    @DisplayName("Calculo correctamente los metros cuadrados de una habitación.")
    void correctCalculationSquareMetters(){
        //Arrange
        RoomRequestDTO room = new RoomRequestDTO("Baño",20,10);
        Integer expected = 20*10;

        //Act
        Integer response = CalculateHandler.calculateSquareMeters(room);

        //Assert
        Assertions.assertEquals(expected,response);
    }

    @Test
    @DisplayName("Calculo correctamente el precio de una casa.")
    void correctCalculationPriceTest(){
        //Arrange
        Integer expected = 20*10;

        //Act
        Integer response = CalculateHandler.calculatePrice(20,10);

        //Assert
        Assertions.assertEquals(expected,response);
    }


    @Test
    @DisplayName("Calculo correctamente los metros cuadrados de una casa a partir de sus habitaciones")
    void correctSquareMetersPerHouseCalculationTest(){
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",10,2));
        house.setRooms(rooms);
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        Integer expected = (15*3 + 10*2);

        //Act
        Integer result = CalculateHandler.setRooms(house,houseResponseDTO);

        //Assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    @DisplayName("Recibo error por enviar una locación inválida.")
    void errorWhenSendingInvalidLocationTest() throws LocationNotFound {
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        LocationDTO locationDTO = new LocationDTO("Capital",900);
        house.setLocation(locationDTO);

        //Mock
        when(priceRepository.findPriceLocation("Capital")).thenThrow(LocationNotFound.class);


        //Assert
        Assertions.assertThrows(LocationNotFound.class,
                ()->calculateHandler.getLocationPrice(house,priceRepository));
        verify(priceRepository,atLeast(1)).findPriceLocation("Capital");
    }


    @Test
    @DisplayName("Recibo error por enviar un precio por metro cuadrado inválido.")
    void errorWhenSendingIncorrectPriceLocationTest() throws LocationNotFound {
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        LocationDTO locationDTO = new LocationDTO("Palermo",900);
        house.setLocation(locationDTO);

        //Mock
        LocationDTO palermoMock = new LocationDTO("Palermo",1000);
        when(priceRepository.findPriceLocation("Palermo")).thenReturn(palermoMock);


        //Assert
        Assertions.assertThrows(IncorrectLocationPrice.class,
                ()->calculateHandler.getLocationPrice(house,priceRepository));
        verify(priceRepository,atLeast(1)).findPriceLocation("Palermo");
    }



    @Test
    @DisplayName("Determino correctamente la habitación mas grande.")
    void correctBiggestRoomResponseTest(){
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",10,2));
        house.setRooms(rooms);
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        String expected = "Cocina";

        //Act
        CalculateHandler.setRooms(house,houseResponseDTO);

        //Assert
        Assertions.assertEquals(expected,houseResponseDTO.getBiggest().getRoomName());
    }

    @Test
    @DisplayName("En caso de tener 2 habitaciones igual de grandes, me quedo con la primera cargada.")
    void correctBiggestRoomBetweenTwoResponseTest(){
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",3,15));
        house.setRooms(rooms);
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        String expected = "Cocina";

        //Act
        CalculateHandler.setRooms(house,houseResponseDTO);

        //Assert
        Assertions.assertEquals(expected,houseResponseDTO.getBiggest().getRoomName());
    }

    @Test
    @DisplayName("Calculo correctamente los metros cuadrados de una única habitación enviada por lista.")
    void correctSquareMetersPerRoomCalculationTest(){
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",10,2));
        house.setRooms(rooms);
        HouseResponseDTO houseResponse = new HouseResponseDTO();
        Integer expected = (15*3);

        //Act
        CalculateHandler.setRooms(house,houseResponse);

        //Assert
        Assertions.assertEquals(expected,houseResponse.getBiggest().getRoomSize());
    }

}
