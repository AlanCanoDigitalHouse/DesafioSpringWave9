package com.mercadolibre.calculadorametroscuadrados.service;


import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PropertyDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.*;
import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CalculateService implements ICalculateService {

    private final String PATH = "classpath:static/price.json";

    private final IEnviromentService environmentService;
    private final IDistrictService districtService;

    public CalculateService(EnvironmentService environmentService, IDistrictService districtService) {
        this.environmentService = environmentService;
        this.districtService = districtService;
    }

    /* TODO: US-0001*/
    @Override
    public PropertyTotalSquareMetersResponse calculateTotalSquareMeters(PropertyDTO house) throws DistrictNotFoundException {
        districtService.existDistrict(house.getDistrict(), PATH);
        Double squareTotal = environmentService.calculateRoomSquareFeet(house);
        return new PropertyTotalSquareMetersResponse(house.getProp_name(), house.getDistrict(), squareTotal);
    }

    /*TODO: US-0002*/
    @Override
    public PropertyValueResponse calculatePropertyValue(PropertyDTO house) throws DistrictNotFoundException {
        districtService.existDistrict(house.getDistrict(), PATH);
        return new PropertyValueResponse(house.getProp_name(), house.getDistrict(), environmentService.calculatePrice(house.getDistrict(), environmentService.calculateRoomSquareFeet(house)));
    }

    /*TODO: US-0003*/
    @Override
    public PropertyEnvironmentBiggerResponse calculateBiggerEnvironment(PropertyDTO house) throws DistrictNotFoundException {
        districtService.existDistrict(house.getDistrict(), PATH);
        EnvironmentDTO bigger = environmentService.calculateBiggerEnvironment(house);
        return new PropertyEnvironmentBiggerResponse(house.getProp_name(), house.getDistrict(), bigger);
    }

    /*TODO: US-0004*/
    @Override
    public PropertyEnvironmentSquareMetersResponse calculateTotalSquareMetersEnviroment(PropertyDTO house) throws DistrictNotFoundException {
        districtService.existDistrict(house.getDistrict(), PATH);
        return new PropertyEnvironmentSquareMetersResponse(house.getProp_name(), house.getDistrict(), environmentService.calculateTotalSquareMetersEnviroment(house));
    }
}
