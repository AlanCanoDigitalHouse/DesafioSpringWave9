package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dtos.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;

public interface DistrictRepository {
    Double findPriceDistrict(DistrictDTO district) throws DistrictNotFoundException;
}
