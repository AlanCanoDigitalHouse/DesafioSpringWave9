package com.mercadolibre.tucasita.service;

import com.mercadolibre.tucasita.domain.House;
import com.mercadolibre.tucasita.dto.RoomDTO;

import java.util.List;

public interface HouseCalculateService {
    double calculateTotalSquareMeters(House house);
    double calculateHousePrice(House house);
    RoomDTO findBiggestRoom(House house);
    List<RoomDTO> calculateRoomSizes(House house);
}
