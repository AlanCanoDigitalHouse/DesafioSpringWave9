package com.bootcamp.desafio2.services.implementation;

import com.bootcamp.desafio2.dtos.request.DistrictDto;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.request.EnvironmentDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.services.IDistrictService;
import com.bootcamp.desafio2.services.IPropertyService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PropertyService implements IPropertyService {

    IDistrictService districtService;

    public PropertyService(IDistrictService districtService) {
        this.districtService = districtService;
    }

    private static final String MESSAGE_DISTRICT_NOT_FOUND = "No existe un barrio que corresponda a los datos enviados";
    private static final String CALCULATE_ERROR = "No se logro calcular el precio de la propiedad";

    @Override
    public ResponseDto calculatePrice(PropertyDto property) throws IOException, DistrictNotFoundException {
        Double totalArea = 0D;
        double maxArea = 0D;
        String aux = "";
        this.validateDistrict(property.getDistrict());

        for (EnvironmentDto Environment: property.getEnvironments()) {
            Double area = Environment.getEnvironment_width() * Environment.getEnvironment_length();
            Environment.setSquareMeters(area);
            totalArea = totalArea + area;
            if (area > maxArea) {
                aux = Environment.getEnvironment_name();
                maxArea = area;
            }
        }
        return new ResponseDto(totalArea, totalArea *property.getDistrict().getDistrict_price(),
                aux, property.getEnvironments());

    }

    private void validateDistrict(DistrictDto district) throws DistrictNotFoundException, IOException {
        if (!districtService.neighborhoodExist(
                district.getDistrict_name(),
                district.getDistrict_price()))
            throw new DistrictNotFoundException(MESSAGE_DISTRICT_NOT_FOUND);
    }

}
