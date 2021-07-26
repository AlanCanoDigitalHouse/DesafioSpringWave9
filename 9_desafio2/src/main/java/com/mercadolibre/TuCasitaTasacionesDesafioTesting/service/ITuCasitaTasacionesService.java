package com.mercadolibre.TuCasitaTasacionesDesafioTesting.service;

import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.HouseRequestDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.HouseResponseDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.RoomResponseDto;

public interface ITuCasitaTasacionesService {

    void enterData(HouseRequestDto house);

    HouseResponseDto calculateHouseArea();

    RoomResponseDto calculateBiggestRoom();

    HouseResponseDto calculatePriceByLocation();

    HouseResponseDto calculateRoomArea();
}
