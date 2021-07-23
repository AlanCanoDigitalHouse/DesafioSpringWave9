package com.mercadolibre.tucasitatasaciones.repository;

import com.mercadolibre.tucasitatasaciones.dto.DistrictDTO;

import java.util.List;

public interface IDistrictRepository {

    DistrictDTO getDistrictByName(String name);

    List<DistrictDTO> getAllDistricts();

}
