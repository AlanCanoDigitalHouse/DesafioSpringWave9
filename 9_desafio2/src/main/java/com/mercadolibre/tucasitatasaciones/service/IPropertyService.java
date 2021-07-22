package com.mercadolibre.tucasitatasaciones.service;

import com.mercadolibre.tucasitatasaciones.dto.request.PropertyRequestDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.*;

public interface IPropertyService {

    PropertyTotalAreaDTO calculatePropertyTotalArea(PropertyRequestDTO propData);

    PropertyValuationDTO valuateProperty(PropertyRequestDTO propData);

    LargestEnvironmentDTO determineLargestEnvironment(PropertyRequestDTO propData);

    PropertyEnvironmentsAreaDTO calculateEnvironmentsArea(PropertyRequestDTO propData);

}
