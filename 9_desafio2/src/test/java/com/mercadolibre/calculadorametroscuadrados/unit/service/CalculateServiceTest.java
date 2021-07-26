package com.mercadolibre.calculadorametroscuadrados.unit.service;

import com.mercadolibre.calculadorametroscuadrados.dto.requests.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.requests.RoomRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectLocationPrice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepository;
import com.mercadolibre.calculadorametroscuadrados.services.CalculateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private CalculateServiceImpl calculateService;

    //Nota, se debería haber mockeado tambien la clase ServiceHandler pero Junit 5 no permite mockear metodos estaticos
    

    @Test
    void correctPriceCalculationTest() throws LocationNotFound, IncorrectLocationPrice {
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        house.setName("Mikasa");
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",10,2));
        house.setRooms(rooms);
        LocationDTO locationDTO = new LocationDTO("Palermo",1000);
        house.setLocation(locationDTO);
        Integer expected = (15*3 + 10*2) * 1000;

        //Mock
        LocationDTO palermoMock = new LocationDTO("Palermo",1000);
        when(priceRepository.findPriceLocation("Palermo")).thenReturn(palermoMock);

        //Act
        HouseResponseDTO houseResponseDTO = calculateService.calculate(house);

        //Assert
        verify(priceRepository,atLeast(1)).findPriceLocation("Palermo");
        Assertions.assertEquals(expected,houseResponseDTO.getPrice());
    }

    @Test
    void correctSquareMetersPerHouseCalculationTest() throws LocationNotFound, IncorrectLocationPrice {
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        house.setName("Mikasa");
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",10,2));
        house.setRooms(rooms);
        LocationDTO locationDTO = new LocationDTO("Palermo",1000);
        house.setLocation(locationDTO);
        Integer expected = (15*3 + 10*2);

        //Mock
        LocationDTO palermoMock = new LocationDTO("Palermo",1000);
        when(priceRepository.findPriceLocation("Palermo")).thenReturn(palermoMock);

        //Act
        HouseResponseDTO houseResponseDTO = calculateService.calculate(house);

        //Assert
        verify(priceRepository,atLeast(1)).findPriceLocation("Palermo");
        Assertions.assertEquals(expected,houseResponseDTO.getSquareMeters());
    }

    @Test
    void errorWhenSendingInvalidLocationTest() throws LocationNotFound {
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        house.setName("Mikasa");
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",10,2));
        house.setRooms(rooms);
        LocationDTO locationDTO = new LocationDTO("Capital",900);
        house.setLocation(locationDTO);

        //Mock
        when(priceRepository.findPriceLocation("Capital")).thenThrow(LocationNotFound.class);


        //Assert
        Assertions.assertThrows(LocationNotFound.class,
                ()->calculateService.calculate(house));
        verify(priceRepository,atLeast(1)).findPriceLocation("Capital");
    }


    @Test
    void errorWhenSendingIncorrectPriceLocationTest() throws LocationNotFound {
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        house.setName("Mikasa");
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",10,2));
        house.setRooms(rooms);
        LocationDTO locationDTO = new LocationDTO("Palermo",900);
        house.setLocation(locationDTO);

        //Mock
        LocationDTO palermoMock = new LocationDTO("Palermo",1000);
        when(priceRepository.findPriceLocation("Palermo")).thenReturn(palermoMock);


        //Assert
        Assertions.assertThrows(IncorrectLocationPrice.class,
                ()->calculateService.calculate(house));
        verify(priceRepository,atLeast(1)).findPriceLocation("Palermo");
    }



    @Test
    void correctBiggestRoomResponseTest() throws LocationNotFound, IncorrectLocationPrice {
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        house.setName("Mikasa");
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",10,2));
        house.setRooms(rooms);
        LocationDTO locationDTO = new LocationDTO("Palermo",1000);
        house.setLocation(locationDTO);
        String expected = "Cocina";

        //Mock
        LocationDTO palermoMock = new LocationDTO("Palermo",1000);
        when(priceRepository.findPriceLocation("Palermo")).thenReturn(palermoMock);

        //Act
        HouseResponseDTO houseResponseDTO = calculateService.calculate(house);

        //Assert
        verify(priceRepository,atLeast(1)).findPriceLocation("Palermo");
        Assertions.assertEquals(expected,houseResponseDTO.getBiggest().getRoomName());
    }

    @Test
    void correctSquareMetersPerRoomCalculationTest() throws LocationNotFound, IncorrectLocationPrice {
        //Arrange
        HouseRequestDTO house = new HouseRequestDTO();
        house.setName("Mikasa");
        ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("Cocina",15,3));
        rooms.add(new RoomRequestDTO("Baño",10,2));
        house.setRooms(rooms);
        LocationDTO locationDTO = new LocationDTO("Palermo",1000);
        house.setLocation(locationDTO);
        Integer expected = (15*3);

        //Mock
        LocationDTO palermoMock = new LocationDTO("Palermo",1000);
        when(priceRepository.findPriceLocation("Palermo")).thenReturn(palermoMock);

        //Act
        HouseResponseDTO houseResponseDTO = calculateService.calculate(house);

        //Assert
        verify(priceRepository,atLeast(1)).findPriceLocation("Palermo");
        Assertions.assertEquals(expected,houseResponseDTO.getBiggest().getRoomSize());
    }


}
