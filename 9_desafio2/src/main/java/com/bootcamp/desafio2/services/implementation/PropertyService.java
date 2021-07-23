package com.bootcamp.desafio2.services.implementation;

import com.bootcamp.desafio2.dtos.request.DistrictDto;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.request.EnvironmentDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.BusinessException;
import com.bootcamp.desafio2.services.IDistrictService;
import com.bootcamp.desafio2.services.IPropertyService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class PropertyService implements IPropertyService {

    IDistrictService districtService;

    public PropertyService(IDistrictService districtService) {
        this.districtService = districtService;
    }

    private static final String MESSAGE_DISTRICT_NOT_FOUND = "No existe un barrio que corresponda a los datos enviados";
    private static final String BUSINESS_CALCULATE_ERROR = "No se logro calcular el precio de la propiedad";
    private static final String BUSINESS_NO_ENVIRONMENTS_ERROR = "La propiedad debe tener minimo un ambiente";
    private static final String BUSINESS_DISTRICT_ERROR = "La propiedad debe tener definido el barrio";
    private static final String BUSINESS_PROPERTY_NO_NAME_ERROR = "La propiedad debe tener un nombre definido";
    private static final String BUSINESS_ENVIRONMENTS_ERROR = "Los ambientes enviados tienen información incompleta o " +
            "presentan valores negativos";

    @Override
    public ResponseDto calculatePrice(PropertyDto property) throws BusinessException, IOException {
        Double totalArea = 0D;
        double maxArea = 0D;
        String aux = "";
        this.validateProperty(property);
        this.validateDistrict(property.getDistrict());
        try {
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
        } catch (Exception e) {
            throw new BusinessException(BUSINESS_CALCULATE_ERROR);
        }
    }

    private void validateProperty(PropertyDto property) throws BusinessException {
        if (Objects.isNull(property.getProp_name()) || property.getProp_name().isEmpty())
            throw new BusinessException(BUSINESS_PROPERTY_NO_NAME_ERROR);
        if (Objects.isNull(property.getEnvironments()) || property.getEnvironments().isEmpty())
            throw new BusinessException(BUSINESS_NO_ENVIRONMENTS_ERROR);
        if (Objects.isNull(property.getDistrict()))
            throw new BusinessException(BUSINESS_DISTRICT_ERROR);
        if (validateEnvironments(property.getEnvironments()))
            throw new BusinessException(BUSINESS_ENVIRONMENTS_ERROR);
    }

    private boolean validateEnvironments(List<EnvironmentDto> environments) {
        return environments.stream().anyMatch(x ->
                Objects.isNull(x.getEnvironment_name()) ||
                Objects.isNull(x.getEnvironment_width()) ||
                x.getEnvironment_width() < 0 ||
                Objects.isNull(x.getEnvironment_length()) ||
                x.getEnvironment_length() < 0);
    }

    private void validateDistrict(DistrictDto district) throws DistrictNotFoundException, IOException {
        if (!districtService.neighborhoodExist(
                district.getDistrict_name(),
                district.getDistrict_price()))
            throw new DistrictNotFoundException(MESSAGE_DISTRICT_NOT_FOUND);
    }

}
