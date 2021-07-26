package com.mercadolibre.TuCasitaTasacionesDesafioTesting.unitTests;

import com.mercadolibre.TuCasitaTasacionesDesafioTesting.entity.District;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.HouseRequestDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.RoomRequestDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.HouseResponseDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.RoomResponseDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions.EmptyListException;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions.InvalidDistrictException;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions.NullListException;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.repositories.TuCasitaTasacionesRepository;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.service.TuCasitaTasacionesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TuCasitaTasacionesServiceTest {

    @Mock
    TuCasitaTasacionesRepository repository;
    @InjectMocks
    TuCasitaTasacionesService service;

    @Test
    @DisplayName("Calculate house area service test - correct case")
    public void shouldCalculateHouseArea() {
        HouseResponseDto response = createHouseResponse();
        HouseRequestDto request = createHouseRequest();
        service.enterData(request);
        Assertions.assertEquals(response.getHouseArea(), service.calculateHouseArea().getHouseArea());
    }

    @Test
    @DisplayName("Calculate house area service test - incorrect case")
    public void shouldNotCalculateHouseArea() {
        HouseResponseDto response = createHouseResponse();
        response.setHouseArea(45.00);
        HouseRequestDto request = createHouseRequest();
        service.enterData(request);
        Assertions.assertNotEquals(response.getHouseArea(), service.calculateHouseArea().getHouseArea());
    }

    @Test
    @DisplayName("Calculate if throws a null list exception - service test - method calculate house area")
    public void shouldThrowsANullListExceptionHouseArea() {
        HouseRequestDto request = createHouseRequest();
        request.setRooms(null);
        service.enterData(request);
        Assertions.assertThrows(NullListException.class, () -> {
            service.calculateHouseArea();
        });
    }
    @Test
    @DisplayName("Calculate if throws an empty list exception - service test - method calculate house area")
    public void shouldThrowsAnEmptyListExceptionHouseArea() {
        HouseRequestDto request = createHouseRequest();
       request.setRooms(new ArrayList<RoomRequestDto>());
        service.enterData(request);
        Assertions.assertThrows(EmptyListException.class, () -> {
            service.calculateHouseArea();
        });
    }

    @Test
    @DisplayName("Calculate price by location - service test - correct case")
    public void shouldCalculatePrice() {
        HouseResponseDto response = createHouseResponse();
        HouseRequestDto request = createHouseRequest();
        District expectedDistrictName = new District("Recoleta", 3500.0);
        when(repository.findPriceByLocation("Recoleta")).thenReturn(Optional.of(expectedDistrictName));
        service.enterData(request);
        Assertions.assertEquals(response.getProp_price(), service.calculatePriceByLocation().getProp_price());

    }

    @Test
    @DisplayName("Calculate price by location - service test - incorrect case")
    public void shouldNotCalculatePrice() {
        HouseResponseDto response = createHouseResponse();
        HouseRequestDto request = createHouseRequest();
        District expectedDistrictName = new District("Belgrano", 3100.0);
        when(repository.findPriceByLocation("Recoleta")).thenReturn(Optional.of(expectedDistrictName));
        service.enterData(request);
        Assertions.assertNotSame(response.getProp_price(), service.calculatePriceByLocation().getProp_price());

    }
    @Test
    @DisplayName("Calculate if throws an invalid district exception - service test - method calculate price by location")
    public void shouldThrowsAnInvalidDistrictException() {
        HouseRequestDto request = createHouseRequest();
        request.setDistrict_name("Villa Urquiza");
        service.enterData(request);
        Assertions.assertThrows(InvalidDistrictException.class, () -> {
            service.calculatePriceByLocation();
        });
    }

    @Test
    @DisplayName("Calculate the biggest room - service test - correct case")
    public void shouldCalculateBiggestRoom() {
        RoomResponseDto biggestRoom = new RoomResponseDto("Cocina", 21.0);
        HouseRequestDto request = createHouseRequest();
        service.enterData(request);
        Assertions.assertEquals(biggestRoom, service.calculateBiggestRoom());
    }

    @Test
    @DisplayName("Calculate the biggest room - service test - incorrect case")
    public void shouldNotCalculateBiggestRoom() {
        RoomResponseDto fakeBiggestRoom = new RoomResponseDto("Living", 9.0);
        HouseRequestDto request = createHouseRequest();
        service.enterData(request);
        Assertions.assertNotEquals(fakeBiggestRoom, service.calculateBiggestRoom());
    }
    @Test
    @DisplayName("Calculate if throws a null list exception - service test - method calculate biggest room")
    public void shouldThrowsANullListExceptionBiggestRoom() {
        HouseRequestDto request = createHouseRequest();
        request.setRooms(null);
        service.enterData(request);
        Assertions.assertThrows(NullListException.class, () -> {
            service.calculateBiggestRoom();
        });
    }
    @Test
    @DisplayName("Calculate if throws an empty list exception - service test - method calculate biggest room")
    public void shouldThrowsAnEmptyListExceptionBiggestRoom() {
        HouseRequestDto request = createHouseRequest();
        request.setRooms(new ArrayList<RoomRequestDto>());
        service.enterData(request);
        Assertions.assertThrows(EmptyListException.class, () -> {
            service.calculateBiggestRoom();
        });
    }

    @Test
    @DisplayName("Calculate the area of each room - service test - correct case")
    public void shouldCalculateRoomsArea() {
        HouseResponseDto responseExpected = createHouseResponse();
        HouseRequestDto request = createHouseRequest();
        service.enterData(request);
        Assertions.assertEquals(responseExpected.getRooms(), service.calculateRoomArea().getRooms());
    }

    @Test
    @DisplayName("Calculate the area of each room - service test - incorrect case")
    public void shouldNotCalculateRoomsArea() {
        HouseResponseDto responseExpected = createHouseResponse();
        HouseRequestDto request = createHouseRequest();
        RoomResponseDto room = new RoomResponseDto("Banio", 24.0);
        responseExpected.getRooms().add(room);
        service.enterData(request);
        Assertions.assertNotEquals(responseExpected.getRooms(), service.calculateRoomArea().getRooms());
    }
    @Test
    @DisplayName("Calculate if throws a null list exception - service test - method calculate room area")
    public void shouldThrowsANullListExceptionRoomsArea() {
        HouseRequestDto request = createHouseRequest();
        request.setRooms(null);
        service.enterData(request);
        Assertions.assertThrows(NullListException.class, () -> {
            service.calculateRoomArea();
        });
    }
    @Test
    @DisplayName("Calculate if throws an empty list exception - service test - method calculate room area")
    public void shouldThrowsAnEmptyListExceptionRoomsArea() {
        HouseRequestDto request = createHouseRequest();
        request.setRooms(new ArrayList<RoomRequestDto>());
        service.enterData(request);
        Assertions.assertThrows(EmptyListException.class, () -> {
            service.calculateRoomArea();
        });
    }
    /**
     * Create a house response
     */
    private HouseResponseDto createHouseResponse() {
        ArrayList<RoomResponseDto> roomsResponse = new ArrayList<>();
        RoomResponseDto room1 = new RoomResponseDto("Cocina", 21.0);
        RoomResponseDto room2 = new RoomResponseDto("Living", 9.0);
        roomsResponse.add(room1);
        roomsResponse.add(room2);
        return new HouseResponseDto(
                " Mi nueva casa",
                30.0,
                105000.0,
                "Recoleta",
                roomsResponse);
    }
    /**
     * Create a house request
     */
    private HouseRequestDto createHouseRequest() {
        ArrayList<RoomRequestDto> roomsRequest = new ArrayList<>();
        RoomRequestDto room1 = new RoomRequestDto("Cocina", 7.0, 3.0);
        RoomRequestDto room2 = new RoomRequestDto("Living", 3.0, 3.0);
        roomsRequest.add(room1);
        roomsRequest.add(room2);
        return new HouseRequestDto(
                " Mi nueva casa",
                "Recoleta",
                roomsRequest);
    }
}
