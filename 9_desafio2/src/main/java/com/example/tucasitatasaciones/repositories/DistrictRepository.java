package com.example.tucasitatasaciones.repositories;

import com.example.tucasitatasaciones.dtos.PropertyDTO;

public interface DistrictRepository {
    Boolean checkIfDistrictExistsFor(PropertyDTO property);
}
