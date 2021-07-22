package com.mercadolibre.desafiotesting.repositories;

import com.mercadolibre.desafiotesting.exceptions.DistrictException;

public interface DistrictRepository {

    String findDistrictByName(String name) throws DistrictException;
}
