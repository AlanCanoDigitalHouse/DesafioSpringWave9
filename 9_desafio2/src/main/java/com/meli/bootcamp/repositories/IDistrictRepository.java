package com.meli.bootcamp.repositories;

import com.meli.bootcamp.dto.DistrictDTO;
import com.meli.bootcamp.exceptions.DistrictException;

public interface IDistrictRepository {

    boolean validateDistrict(String District_name) throws DistrictException;
}
