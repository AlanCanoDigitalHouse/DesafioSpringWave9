package com.desafio.tucasitatasaciones.repository;

import com.desafio.tucasitatasaciones.model.District;

import java.util.Optional;

public interface IDistrictRepository {
    Optional<District> findDistrictByName(String name);
}
