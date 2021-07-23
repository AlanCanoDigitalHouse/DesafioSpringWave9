package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;

public interface DistrictRepository {
    DistrictDTO findDistrictByDistrictName(String districtName);
}
