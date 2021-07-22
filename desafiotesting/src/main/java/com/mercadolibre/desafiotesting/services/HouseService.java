package com.mercadolibre.desafiotesting.services;

import com.mercadolibre.desafiotesting.dto.HouseDto;
import com.mercadolibre.desafiotesting.dto.HouseResponseDto;
import com.mercadolibre.desafiotesting.dto.RequestHouseDto;
import com.mercadolibre.desafiotesting.exceptions.HouseException;

public interface HouseService {

    HouseResponseDto calculate(RequestHouseDto house) throws HouseException;
}
