package com.desafios.test.services;

import com.desafios.test.dtos.request.HouseRequestDTO;
import com.desafios.test.dtos.response.HouseResponseDTO;

public interface CalculateService {
    HouseResponseDTO calculateBiggestRoom(HouseRequestDTO request);
    HouseResponseDTO calculateHouseSquareFeet(HouseRequestDTO request);
    HouseResponseDTO calculatePrice(HouseRequestDTO request);
    HouseResponseDTO calculateRoomSquareFeet(HouseRequestDTO request);

}
