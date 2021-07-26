package com.example.desafiotesting.units.service;

import com.example.desafiotesting.dto.HouseDTO;
import com.example.desafiotesting.dto.HouseResponseDTO;
import com.example.desafiotesting.dto.PriceDTO;
import com.example.desafiotesting.dto.RoomDTO;
import com.example.desafiotesting.repositories.PriceRepositoryImpl;
import com.example.desafiotesting.service.CalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {
    @Mock
    PriceRepositoryImpl priceRepository;

    @InjectMocks
    CalculateService calculateService;

    @Test
    void calculateSquareMeters() {
        HouseDTO houseDTO = this.generateRoom();

        HouseResponseDTO result = calculateService.calculate(houseDTO);
        Integer expected = this.generateRoomSquareMeters();

        Assertions.assertEquals(expected, result.getSquareFeet());
    }

    private Integer generateRoomSquareMeters() {
        return 5*5;
    }

    private HouseDTO generateRoom() {
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setProp_name("Tu casa");
        houseDTO.setDistrict_name("Palermo");

        List<RoomDTO> rooms = new ArrayList<>();

        rooms.add(new RoomDTO("Habitacion", 5, 5));
        houseDTO.setRooms(rooms);
        return houseDTO;
    }

}