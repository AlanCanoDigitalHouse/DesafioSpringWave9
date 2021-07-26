package com.squareMeter.service;

import com.squareMeter.exception.exception.DistrictNotExistsException;

public interface IDistrictService {
    void districtExists(String name) throws DistrictNotExistsException;
}
