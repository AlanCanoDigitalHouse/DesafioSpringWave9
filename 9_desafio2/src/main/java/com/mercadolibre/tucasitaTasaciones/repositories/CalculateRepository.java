package com.mercadolibre.tucasitaTasaciones.repositories;

import com.mercadolibre.tucasitaTasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationNotFound;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationPriceIncorrect;

public interface CalculateRepository {
    DistrictDTO findPriceLocation(String location) throws ExceptionLocationNotFound;

    void checkPrice(DistrictDTO district) throws ExceptionLocationPriceIncorrect, ExceptionLocationNotFound;
}
