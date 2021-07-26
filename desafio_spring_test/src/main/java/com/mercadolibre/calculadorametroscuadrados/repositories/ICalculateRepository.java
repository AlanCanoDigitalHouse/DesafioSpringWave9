package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;

import java.util.Optional;

public interface ICalculateRepository {
    boolean ifDistrictAreaExist(String location);
    void saveHouse(HouseDTO houseDTO, HouseResponseDTO houseResponseDTO);
    boolean delete(String prop_name);
    boolean exists(String houseName);
    Optional<LocationDTO> findStratumByLocationName(String locationName);
}
