package com.squareMeter.service;

import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.EnvironmentResponseDTO;
import com.squareMeter.dto.response.PropertyValueDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.exception.exception.DistrictNotExistsException;

public interface IPropertyService {
    PropertySquareMetersResponseDTO getHouseTotalSquareMeters(PropertyRequestDTO request);
    PropertyValueDTO getHouseValue(PropertyRequestDTO data) throws DistrictNotExistsException;
    EnvironmentResponseDTO getBiggerEnvironment(PropertyRequestDTO propertyRequestDTO);
}
