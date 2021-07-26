package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.*;

public interface IHouseService {

    HouseResponseDTO calculate(HouseTotalSizeRequestDTO houseDTO);

    HousePriceResponseDTO totalPrice(HousePriceRequestDTO houseDTO);

    RoomResponseBiggestDTO biggestRoom(HouseDTO houseDTO);

    HouseSizesResponseDTO countSizes(HouseDTO house);

    String start();
}
