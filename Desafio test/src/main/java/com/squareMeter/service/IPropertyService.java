package com.squareMeter.service;

import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;

public interface IPropertyService {
    PropertySquareMetersResponseDTO getHouseTotalSquareMeters(PropertyRequestDTO request);
}
