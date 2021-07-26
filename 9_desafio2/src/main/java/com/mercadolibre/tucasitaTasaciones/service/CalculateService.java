package com.mercadolibre.tucasitaTasaciones.service;

import com.mercadolibre.tucasitaTasaciones.dto.PropertyDTO;
import com.mercadolibre.tucasitaTasaciones.dto.response.PropertyResponseDTO;
import com.mercadolibre.tucasitaTasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationNotFound;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationPriceIncorrect;

public interface CalculateService {
    PropertyResponseDTO calculate(PropertyDTO house) throws ExceptionLocationNotFound, ExceptionLocationPriceIncorrect;
}
