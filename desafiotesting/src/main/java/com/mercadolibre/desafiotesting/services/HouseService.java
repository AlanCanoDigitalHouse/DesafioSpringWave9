package com.mercadolibre.desafiotesting.services;

import com.mercadolibre.desafiotesting.dto.HouseResponseDto;
import com.mercadolibre.desafiotesting.dto.RequestHouseDto;
import com.mercadolibre.desafiotesting.exceptions.DistrictException;

public interface HouseService {

    HouseResponseDto calculate(RequestHouseDto house) throws DistrictException;
}
