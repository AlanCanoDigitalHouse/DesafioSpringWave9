package com.example.casitas.services;

import com.example.casitas.dtos.*;
import com.example.casitas.exceptions.BadRequestException;
import com.example.casitas.exceptions.DistrictNotFoundException;
import com.example.casitas.repositories.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    DistrictRepository districtRepository;

    public HouseService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public HouseDetailResponseDTO getPrice(HouseDTO house) throws DistrictNotFoundException {
        districtRepository.findDistrictByName(house.getDistrictName());
        Double squareMeters = totalSumEnvironmentsSquareMeters(house.getEnvironments());
        Double price = calculatePrice(squareMeters,house.getDistrictPrice());
        List<EnvironmentResponseDTO> listEnvs = getPriceForEnvs(house.getEnvironments(), house.getDistrictPrice());
        return new HouseDetailResponseDTO(house.getPropertyName(),
                price,
                house.getDistrictName(),
                house.getDistrictPrice(),
                listEnvs);
    }

    public EnvironmentDTO getBiggerEnvironment(HouseDTO house) {
        return calculateBiggerEnvironment(house.getEnvironments());
    }

    public List<EnvironmentResponseDTO> getListEnvironments(HouseDTO house) {
        return getPriceForEnvs(house.getEnvironments(),house.getDistrictPrice());
    }

    private List<EnvironmentResponseDTO> getPriceForEnvs(List<EnvironmentDTO> environments, Double districtPrice) {
        List<EnvironmentResponseDTO> listEnvironments = new ArrayList<>();

        for (EnvironmentDTO env : environments) {
            Double squareMeters = calculateSquareMeters(env.getEnvironmentWidth(), env.getEnvironmentLength());
            Double price = squareMeters*districtPrice;
            listEnvironments.add(new EnvironmentResponseDTO(env.getEnvironmentName(), squareMeters, price));
        }
        return listEnvironments;
    }

    public HouseResponseDTO getSquareMeters(HouseDTO house) {
        double squareMeters = totalSumEnvironmentsSquareMeters(house.getEnvironments());
        return new HouseResponseDTO(house.getPropertyName(), squareMeters);
    }

    private double totalSumEnvironmentsSquareMeters(List<EnvironmentDTO> environments) {
        double total = 0;

        for (EnvironmentDTO env : environments) {
            total+=env.getEnvironmentLength()*env.getEnvironmentWidth();
        }
        return total;
    }

    private Double calculatePrice(double squareMeters, double districtPrice) {
        return squareMeters*districtPrice;
    }

    private Double calculateSquareMeters(double length,double width) {
        return length*width;
    }

    private EnvironmentDTO calculateBiggerEnvironment(List<EnvironmentDTO> environment) {
        Optional<EnvironmentDTO> envDTO = environment.stream().max(Comparator.comparing(o -> calculateSquareMeters(o.getEnvironmentLength(), o.getEnvironmentWidth())));

        if(envDTO.isPresent()) {
            return envDTO.get();
        } else {
            throw new BadRequestException("Ingresar 2 o mas ambientes");
        }
    }
}
