package com.meli.bootcamp.services;

import com.meli.bootcamp.dto.EnvironmentDetailsDTO;
import com.meli.bootcamp.dto.ValuationDTO;
import com.meli.bootcamp.dto.request.PropertyDTO;
import com.meli.bootcamp.exceptions.DistrictException;
import com.meli.bootcamp.model.Property;
import com.meli.bootcamp.repositories.DistrictRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServicesImp implements IPropertyServices {

    @Autowired
    DistrictRepositoryImp districtRepository;

//    public PropertyServicesImp(DistrictRepositoryImp districtRepo) {
//        this.districtRepository = districtRepo;
//    }

    @Override
    public ValuationDTO valuation(PropertyDTO propertyDTO) throws DistrictException {
        districtRepository.validateDistrict(propertyDTO.getDistrict().getDistrict_name());
        Property newProperty = new Property(propertyDTO);
        newProperty.calculateValuation(propertyDTO.getDistrict().getDistrict_price());
        ValuationDTO response = new ValuationDTO(newProperty);
        response.setBiggest_environment(new EnvironmentDetailsDTO(newProperty.biggestEnvironment()));
        return response;
    }



}
