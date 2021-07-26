package com.example.casitas.repositories;

import com.example.casitas.exceptions.DistrictNotFoundException;

public interface DistrictRepository {

    String findDistrictByName(String districtName) throws DistrictNotFoundException;

}