package com.example.tucasitatasaciones.services;

import com.example.tucasitatasaciones.dtos.DistrictDTO;
import com.example.tucasitatasaciones.dtos.EnviromentDTO;
import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.dtos.response.PropertyResponseDTO;
import com.example.tucasitatasaciones.exceptions.DistrictException;
import com.example.tucasitatasaciones.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import static com.example.tucasitatasaciones.handlers.ServiceHandlers.*;

@Service
public class PropertyServiceImpl implements PropertyService{

    PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyResponseDTO calculateProperty(PropertyDTO property) throws DistrictException {
        if(!propertyRepository.checkIfDistrictExistsFor(property))
            throw new DistrictException(property.getDistrict().getDistrict_name());
        PropertyResponseDTO propertyResponseDTO = new PropertyResponseDTO();
        property.setEnviroments(calculateMt2FromEveryEnviroment(property));
        propertyResponseDTO.setEnviroments(property.getEnviroments());
        propertyResponseDTO.setTotalMts2(calculateMts2FromProperty(property));
        propertyResponseDTO.setTotalPrice(calculatePriceOfProperty(property));
        propertyResponseDTO.setBiggestEnviroment(biggestEnviromentFromProperty(property));
        return propertyResponseDTO;
    }
}
