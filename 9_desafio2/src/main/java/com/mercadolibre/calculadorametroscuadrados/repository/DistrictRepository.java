package com.mercadolibre.calculadorametroscuadrados.repository;

import com.mercadolibre.calculadorametroscuadrados.model.DistrictDAO;

public interface DistrictRepository {

    DistrictDAO findByName(String name);

    void save(DistrictDAO district);

    boolean delete(String name);

    DistrictDAO update(DistrictDAO district);
}
