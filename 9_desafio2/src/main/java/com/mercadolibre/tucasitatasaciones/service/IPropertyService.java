package com.mercadolibre.tucasitatasaciones.service;

import com.mercadolibre.tucasitatasaciones.dto.request.PropertyRequestDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.EnvironmentAreaDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.LargestEnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.PropertyTotalAreaDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.PropertyValuationDTO;

import java.util.List;

public interface IPropertyService {

    PropertyTotalAreaDTO calculatePropertyTotalArea(PropertyRequestDTO propData);

    PropertyValuationDTO valuateProperty(PropertyRequestDTO propData);

    LargestEnvironmentDTO determineLargestEnvironment(PropertyRequestDTO propData);

    List<EnvironmentAreaDTO> calculateEnvironmentsArea(PropertyRequestDTO propData);

}
