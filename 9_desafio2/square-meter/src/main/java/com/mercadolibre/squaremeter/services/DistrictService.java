package com.mercadolibre.squaremeter.services;

import com.mercadolibre.squaremeter.dtos.DistrictDTO;
import com.mercadolibre.squaremeter.dtos.request.Home;
import com.mercadolibre.squaremeter.dtos.response.InfoHome;
import com.mercadolibre.squaremeter.exceptions.DistrictNotExist;

import java.util.List;

public interface DistrictService {

    InfoHome postHouseReport(Home home) throws DistrictNotExist;

    List<DistrictDTO> getAllDistrict();
}
