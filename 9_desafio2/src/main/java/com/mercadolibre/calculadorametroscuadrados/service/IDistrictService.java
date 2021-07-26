package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;

public interface IDistrictService {
    public void existDistrict(DistrictDTO district, String path) throws DistrictNotFoundException;
}
