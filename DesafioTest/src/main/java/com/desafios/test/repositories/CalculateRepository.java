package com.desafios.test.repositories;

import com.desafios.test.dtos.DistrictDTO;

public interface CalculateRepository {
    DistrictDTO findPriceDistrict(String location);
}
