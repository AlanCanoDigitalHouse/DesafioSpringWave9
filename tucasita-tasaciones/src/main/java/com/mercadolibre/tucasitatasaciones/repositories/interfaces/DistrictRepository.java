package com.mercadolibre.tucasitatasaciones.repositories.interfaces;

import com.mercadolibre.tucasitatasaciones.dtos.req.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;

public interface DistrictRepository {

    DistrictDTO findDistrictBy(String name) throws DistrictNotFoundException;

}
