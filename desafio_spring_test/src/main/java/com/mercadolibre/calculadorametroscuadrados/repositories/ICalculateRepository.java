package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;

public interface ICalculateRepository {
    LocationDTO findPriceLocation(String location);
    boolean ifDistrictAreaExist(String location);
}
