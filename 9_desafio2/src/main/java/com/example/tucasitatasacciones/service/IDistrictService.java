package com.example.tucasitatasacciones.service;

import com.example.tucasitatasacciones.exception.exception.DistrictNotExistsException;

public interface IDistrictService {
    void districtExists(String name) throws DistrictNotExistsException;
}
