package com.mercado_libre.bootcamp.desafio2.services.house.implementation;

import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.RoomRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.RoomResponseDTO;
import com.mercado_libre.bootcamp.desafio2.services.house.HouseCalculatorServiceI;
import com.mercado_libre.bootcamp.desafio2.services.neighborhood.implementation.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseCalculatorService implements HouseCalculatorServiceI {

    @Autowired
    NeighborhoodService neighborhoodService;

    @Override
    public HouseResponseDTO calculate(HouseRequestDTO houseRequest) {

        RoomRequestDTO largestRoom = findLargestRoom(houseRequest.getEnvironments());

        return HouseResponseDTO.builder()
                .squaresMeters(calculateSquareMeters(houseRequest.getEnvironments()))
                .valuePerSquaresMeters(calculateValuePerMeter(houseRequest.getEnvironments(), houseRequest.getDistrict().getDistrict_name(), houseRequest.getDistrict().getDistrict_price()))
                .largestRoom(new RoomResponseDTO(largestRoom.getEnvironment_name(),largestRoom.getSquareMeters()))
                .environments(getRooms(houseRequest.getEnvironments()))
                .build();
    }

    private RoomRequestDTO findLargestRoom(List<RoomRequestDTO> rooms) {
        return rooms.stream()
                .sorted((x, y) -> Double.compare(y.getSquareMeters(), x.getSquareMeters()))
                .findFirst()
                .orElse(null);
    }

    private double calculateValuePerMeter(List<RoomRequestDTO> rooms, String districtName, Double districtPrice) {
        neighborhoodService.checkIfNeighborhoodIsValid(districtName, districtPrice);
        return calculateSquareMeters(rooms) * districtPrice;
    }

    private double calculateSquareMeters(List<RoomRequestDTO> rooms) {
        return rooms.stream().map(RoomRequestDTO::getSquareMeters).reduce(Double.valueOf(0), Double::sum);
    }

    private List<RoomResponseDTO> getRooms(List<RoomRequestDTO> rooms) {
        return rooms.stream().map(r -> new RoomResponseDTO(r.getEnvironment_name(), r.getSquareMeters())).collect(Collectors.toList());
    }
}
