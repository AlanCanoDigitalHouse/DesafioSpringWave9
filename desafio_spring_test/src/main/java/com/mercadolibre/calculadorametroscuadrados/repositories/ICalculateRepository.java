package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;

public interface ICalculateRepository {
    boolean ifDistrictAreaExist(String location);
    void saveHouse(HouseDTO houseDTO, HouseResponseDTO houseResponseDTO);
}
