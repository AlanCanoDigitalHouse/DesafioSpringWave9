package com.mercadolibre.squaremeter.repository;

import com.mercadolibre.squaremeter.dtos.DistrictDTO;

import java.util.List;

public interface DistrictRepository {
    Boolean findDistrictByName(String name);

    List<DistrictDTO> findDistrictAll();

}