package com.mercadolibre.tucasitatasaciones.service;

import com.mercadolibre.tucasitatasaciones.dto.request.PropertyRequestDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.EnvironmentAreaDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.LargestEnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.PropertyTotalAreaDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.PropertyValuationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService implements IPropertyService {

    @Override
    public PropertyTotalAreaDTO calculatePropertyTotalArea(PropertyRequestDTO propData) {
        return null;
    }

    @Override
    public PropertyValuationDTO valuateProperty(PropertyRequestDTO propData) {
        return null;
    }

    @Override
    public LargestEnvironmentDTO determineLargestEnvironment(PropertyRequestDTO propData) {
        return null;
    }

    @Override
    public List<EnvironmentAreaDTO> calculateEnvironmentsArea(PropertyRequestDTO propData) {
        return null;
    }
}
