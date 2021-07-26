package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;

public interface PriceRepository {

    LocationDTO findPriceLocation(String location) throws LocationNotFound;
}
