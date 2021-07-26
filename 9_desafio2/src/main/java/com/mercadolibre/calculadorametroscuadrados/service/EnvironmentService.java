package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PropertyDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentTotalMetersResponse;
import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvironmentService implements IEnviromentService {

    /*Calcula el total de Metros cuadrados de la propiedad*/
    @Override
    public Double calculateRoomSquareFeet(PropertyDTO house) {
        return house.getEnvironments()
                .stream()
                .reduce(0d, (subtotal, room) -> subtotal + room.getSquareFeet(), Double::sum);
    }

    @Override
    public List<EnvironmentTotalMetersResponse> calculateTotalSquareMetersEnviroment(PropertyDTO house) {
        return house.getEnvironments()
                .stream()
                .map((environmentDTO) -> new EnvironmentTotalMetersResponse(environmentDTO))
                .collect(Collectors.toList());
    }

    /*Obtiene el ambiente mas grande*/
    @Override
    public EnvironmentDTO calculateBiggerEnvironment(PropertyDTO house) {
        return house.getEnvironments()
                .stream()
                .max(Comparator.comparing(EnvironmentDTO::getSquareFeet)).get();
    }

    /*Precio total de la propiedad*/
    @Override
    public Double calculatePrice(DistrictDTO district, Double totalSquare) {
        return totalSquare * district.getDistrict_price();
    }
}
