package com.example.tucasitatasacciones.service;

import com.example.tucasitatasacciones.dto.request.property.PropertyRequestDTO;
import com.example.tucasitatasacciones.dto.response.environment.EnvironmentResponseDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertySquareMetersResponseDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertyValueDTO;
import com.example.tucasitatasacciones.exception.exception.DistrictNotExistsException;

public interface IPropertyService {
    PropertySquareMetersResponseDTO getHouseTotalSquareMeters(PropertyRequestDTO request);

    PropertyValueDTO getHouseValue(PropertyRequestDTO data) throws DistrictNotExistsException;

    EnvironmentResponseDTO getBiggerEnvironment(PropertyRequestDTO propertyRequestDTO);
}
