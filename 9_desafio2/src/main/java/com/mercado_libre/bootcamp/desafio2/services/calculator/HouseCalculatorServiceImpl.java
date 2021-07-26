package com.mercado_libre.bootcamp.desafio2.services.calculator;

import com.mercado_libre.bootcamp.desafio2.dtos.EnvironmentDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.mercado_libre.bootcamp.desafio2.services.neighborhood.NeighborhoodService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class HouseCalculatorServiceImpl implements HouseCalculatorService {

    private final NeighborhoodService neighborhoodService;

    public HouseCalculatorServiceImpl(NeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    public HouseResponseDTO calculate(HouseRequestDTO houseRequest) {

        neighborhoodService.checkIfNeighborhoodExists(houseRequest.getDistrict());

        return HouseResponseDTO.builder()
                .squaresMeters(calculateSquareMeters(houseRequest.getEnvironments()))
                .valuePerSquaresMeters(calculateValuePerMeter(houseRequest.getEnvironments(), houseRequest.getDistrict().getDistrictPrice()))
                .biggestRoom(findBiggestRoom(houseRequest.getEnvironments()))
                .rooms(houseRequest.getEnvironments())
                .build();
    }

    private EnvironmentDTO findBiggestRoom(List<EnvironmentDTO> rooms) {
        return rooms.stream().max(Comparator.comparingDouble(EnvironmentDTO::getSquareMeters))
                .orElse(null);
    }

    private double calculateValuePerMeter(List<EnvironmentDTO> rooms, Double districtPrice) {
        return calculateSquareMeters(rooms) * districtPrice;
    }

    private double calculateSquareMeters(List<EnvironmentDTO> rooms) {
        return rooms.stream().map(EnvironmentDTO::getSquareMeters)
                .reduce(0.0, Double::sum);
    }
}
