package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertyPriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertySquareFeetDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.RoomResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.repository.NotFoundException;
import com.mercadolibre.calculadorametroscuadrados.exception.service.ServiceException;
import com.mercadolibre.calculadorametroscuadrados.model.DistrictDAO;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CalculateServiceImpl implements CalculateService {

    private DistrictRepository districtRepository;

    public CalculateServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public HouseResponseDTO calculate(HouseDTO house) {
        HouseResponseDTO response = new HouseResponseDTO();
        calculateRoomSquareFeet(house, response);

        DistrictDAO district;
        try {
            district = districtRepository.findByName(house.getDistrict().getDistrict_name());
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new ServiceException("District Not found");
        }
        double result = calculatePrice(response.getSquareFeet(),district.getDistrict_price());
        response.setPrice(result);
        return response;
    }

    @Override
    public PropertySquareFeetDTO calculatePropertySquareFeet(HouseDTO property) {
        Double result = property.getEnvironments()
                .stream()
                .mapToDouble(roomDTO -> calculateSquareFeet.apply(roomDTO)).sum();
        final PropertySquareFeetDTO response = new PropertySquareFeetDTO();
        response.setProperty(property);
        response.setSquareFeet(result);
        return response;
    }

    @Override
    public PropertyPriceDTO calculatePropertyPrice(HouseDTO property) {
        double totalSquareFeet = property.getEnvironments()
                .stream()
                .mapToDouble(roomDTO -> calculateSquareFeet.apply(roomDTO)).sum();
        DistrictDAO district;
        try {
            district = districtRepository.findByName(property.getDistrict().getDistrict_name());
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new ServiceException("District Not found");
        }
        double finalPrice = calculatePrice(totalSquareFeet,district.getDistrict_price());
        final PropertyPriceDTO response = new PropertyPriceDTO();
        response.setProperty(property);
        response.setPrice(finalPrice);
        return response;
    }

    @Override
    public RoomResponseDTO getBiggestEnvironment(HouseDTO property) {
        return property.getEnvironments()
                .stream()
                .map(roomDTO -> {
                    final RoomResponseDTO response = new RoomResponseDTO();
                    response.setEnvironment(roomDTO);
                    response.setSquare_feet(calculateSquareFeet.apply(roomDTO));
                    return response;
                })
                .max(Comparator.comparingDouble(RoomResponseDTO::getSquare_feet)).get();
    }

    @Override
    public List<RoomResponseDTO> calculateSquareFeetPerEnvironment(HouseDTO property) {
        return property.getEnvironments()
                .stream()
                .map( roomDTO -> {
                    final RoomResponseDTO result = new RoomResponseDTO();
                    result.setEnvironment(roomDTO);
                    result.setSquare_feet(calculateSquareFeet.apply(roomDTO));
                    return result;
                })
                .collect(Collectors.toList());
    }

    private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
        double totalSquareFeet = 0;
        RoomDTO biggest = null;
        double maxRoom = 0;
        for (RoomDTO room : house.getEnvironments()) {
            double squareFeet = calculateSquareFeet.apply(room);
            totalSquareFeet += squareFeet;
            if (biggest == null || squareFeet > maxRoom) {
                biggest = room;
                maxRoom = squareFeet;
            }
        }
        response.setSquareFeet(totalSquareFeet);
        response.setBiggest(biggest);
    }

    private Function<RoomDTO, Double> calculateSquareFeet = roomDTO -> roomDTO.getEnvironment_width() * roomDTO.getEnvironment_length();
    private double calculatePrice(double result, double price) {
        return result * price;
    }
}
