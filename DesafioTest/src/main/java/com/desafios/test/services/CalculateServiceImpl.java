package com.desafios.test.services;

import com.desafios.test.dtos.request.HouseRequestDTO;
import com.desafios.test.dtos.DistrictDTO;
import com.desafios.test.dtos.response.HouseResponseDTO;
import com.desafios.test.dtos.EnvironmentDTO;
import com.desafios.test.exceptions.NoDistrictFoundException;
import com.desafios.test.repositories.CalculateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CalculateServiceImpl implements CalculateService {

    CalculateRepository calculateRepository;

    public CalculateServiceImpl(CalculateRepository calculateRepository){
        this.calculateRepository = calculateRepository;
    }

    public HouseResponseDTO calculateHouseSquareFeet(HouseRequestDTO request) {
        Double totalSquareFeet = 0.0;
        for (EnvironmentDTO room : request.getEnvironments())
            totalSquareFeet += room.getSquareFeet();
        HouseResponseDTO response = new HouseResponseDTO();
        response.setSquareFeet(totalSquareFeet);
        return response;
    }

    public HouseResponseDTO calculateBiggestRoom(HouseRequestDTO request) {
        Double maxRoom = 0.0;
        EnvironmentDTO biggestRoom = null;
        for (EnvironmentDTO room : request.getEnvironments()) {
            if (biggestRoom == null || room.getSquareFeet() > maxRoom) {
                biggestRoom = room;
                maxRoom = room.getSquareFeet();
            }
        }
        HouseResponseDTO response = new HouseResponseDTO();
        response.setBiggest(biggestRoom);
        return response;
    }

    public HouseResponseDTO calculatePrice(HouseRequestDTO request) {
        DistrictDTO district = calculateRepository.findPriceDistrict(request.getDistrict().getDistrict_name());
        if (Objects.isNull(district))
            throw new NoDistrictFoundException();
        HouseResponseDTO response = new HouseResponseDTO();
        response.setPrice(calculateHouseSquareFeet(request).getSquareFeet() * district.getDistrict_price());
        return response;
    }

    public HouseResponseDTO calculateRoomSquareFeet(HouseRequestDTO request) {
        HouseResponseDTO response = new HouseResponseDTO();
        List<EnvironmentDTO> roomsResponse = new ArrayList<>();
        for (EnvironmentDTO room : request.getEnvironments())
        {
            room.getSquareFeet();
            roomsResponse.add(room);
        }
        response.setEnvironments(roomsResponse);
        return response;
    }
}
