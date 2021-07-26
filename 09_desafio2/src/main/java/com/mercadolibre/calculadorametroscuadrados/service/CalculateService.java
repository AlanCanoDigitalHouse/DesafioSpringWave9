package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.EnvironmentRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.handlers.CalculatorHandler;
import com.mercadolibre.calculadorametroscuadrados.repositories.IDistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CalculateService implements ICalculateService {

    IDistrictRepository districtRepository;

    public CalculateService(IDistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public HouseResponseDTO calculateHouse(HouseRequestDTO house) {
        HouseResponseDTO response = new HouseResponseDTO(house);
        response.setPrice(totalValueHouse(house));
        response.setSquareFeet(totalSquareFeetHouse(house.getEnvironments()));
        try {
            response.setBiggest(biggestEnvironment(house));
        } catch (NoSuchElementException e) {
            response.setBiggest(null);
        }
        response.setEnvironments(squareFeetEnvironments(house.getEnvironments()));
        return response;
    }

    public EnvironmentResponseDTO biggestEnvironment(HouseRequestDTO house) throws NoSuchElementException {
        EnvironmentRequestDTO environment = house.getEnvironments().stream().max((o1, o2) -> {
            Double mts = o1.getWidth() * o1.getLength();
            return mts.compareTo(o2.getWidth() * o2.getLength());
        }).get();
        return CalculatorHandler.squareFeetEnvironment(environment);
    }

    public Double totalValueHouse(HouseRequestDTO house) {
        DistrictDTO district = districtRepository.findByName(house.getDistrict().getName());
        return totalSquareFeetHouse(house.getEnvironments()) * district.getPrice();
    }

    private List<EnvironmentResponseDTO> squareFeetEnvironments(List<EnvironmentRequestDTO> environments) {
        return environments.stream().map(CalculatorHandler::squareFeetEnvironment).collect(Collectors.toList());
    }


    private Double totalSquareFeetHouse(List<EnvironmentRequestDTO> environments) {
        return environments.stream().mapToDouble(x -> x.getWidth() * x.getLength()).sum();
    }


}
