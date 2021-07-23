package com.mercadolibre.tucasitatasaciones.services.interfaces;

import com.mercadolibre.tucasitatasaciones.dtos.req.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;

public interface DistrictService {

    DistrictDTO findDistrictBy(String name) throws DistrictNotFoundException;

}
