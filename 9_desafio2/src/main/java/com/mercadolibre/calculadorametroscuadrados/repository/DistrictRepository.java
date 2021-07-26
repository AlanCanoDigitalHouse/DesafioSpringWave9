package com.mercadolibre.calculadorametroscuadrados.repository;

import com.mercadolibre.calculadorametroscuadrados.exception.repository.NotFoundException;
import com.mercadolibre.calculadorametroscuadrados.model.DistrictDAO;

public interface DistrictRepository {

    DistrictDAO findByName(String name) throws NotFoundException;

    void save(DistrictDAO district);

    boolean delete(String name) throws NotFoundException;

    DistrictDAO update(DistrictDAO district) throws NotFoundException;
}
