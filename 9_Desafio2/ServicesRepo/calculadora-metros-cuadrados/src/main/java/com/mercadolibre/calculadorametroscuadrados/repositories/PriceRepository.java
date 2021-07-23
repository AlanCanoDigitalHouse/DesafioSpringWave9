package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.NotFoundLocation;

public interface PriceRepository {

    PriceDTO findLocation(String location) throws NotFoundLocation;

}
