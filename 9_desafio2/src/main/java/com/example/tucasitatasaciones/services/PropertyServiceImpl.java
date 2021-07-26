package com.example.tucasitatasaciones.services;

import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.dtos.response.PropertyResponseDTO;
import com.example.tucasitatasaciones.exceptions.DistrictException;
import com.example.tucasitatasaciones.repositories.DistrictRepository;
import org.springframework.stereotype.Service;

import static com.example.tucasitatasaciones.handlers.ServiceHandlers.*;

@Service
public class PropertyServiceImpl implements PropertyService{

    DistrictRepository districtRepository;

    public PropertyServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public PropertyResponseDTO calculateProperty(PropertyDTO property) throws DistrictException {
        if(!districtRepository.checkIfDistrictExistsFor(property))
            throw new DistrictException(property.getDistrict().getDistrict_name());

        property.setEnviroments(calculateMt2FromEveryEnviroment(property));
        return PropertyResponseDTO.builder()
                .enviroments(property.getEnviroments())
                .totalMts2(calculateMts2FromProperty(property))
                .totalPrice(calculatePriceOfProperty(property))
                .biggestEnviroment(biggestEnviromentFromProperty(property))
                .build();
    }
}
