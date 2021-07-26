package com.mercadolibre.calculadorametroscuadrados.repositories;


import com.mercadolibre.calculadorametroscuadrados.dto.Request.DistrictDTO;

public interface IDistrictRepository {
    void save(DistrictDTO district);

    boolean delete(String name);

    boolean exists(DistrictDTO district);

    DistrictDTO findByName(String name);
}