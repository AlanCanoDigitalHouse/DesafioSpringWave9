package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.repository.HouseRepositoryDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DataNotFound;

import java.util.Optional;
import java.util.Set;

public interface IHouseDAO {
    void save(HouseRepositoryDTO house);
    boolean delete(String prop_name);
    boolean exists(HouseDTO house);
    Optional<LocationDTO> findStratumByLocationName(String locationName);
    HouseDTO getAnyHouseDTOFromDB() throws DataNotFound;
}
