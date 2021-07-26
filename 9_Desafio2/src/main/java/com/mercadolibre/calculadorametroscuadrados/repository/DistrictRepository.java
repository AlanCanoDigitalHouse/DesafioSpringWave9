package com.mercadolibre.calculadorametroscuadrados.repository;

import com.mercadolibre.calculadorametroscuadrados.entities.District;

import java.util.List;

public interface DistrictRepository {

    double getPriceByDistrict(String district);

    List<District> getAllDistricts();
}
