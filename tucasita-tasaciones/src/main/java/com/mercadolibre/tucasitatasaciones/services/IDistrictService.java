package com.mercadolibre.tucasitatasaciones.services;

import com.mercadolibre.tucasitatasaciones.dtos.request.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;

public interface IDistrictService {
    DistrictDTO findByName(String location) throws DistrictNotFoundException;
}
