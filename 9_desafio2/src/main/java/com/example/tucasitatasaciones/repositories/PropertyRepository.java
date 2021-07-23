package com.example.tucasitatasaciones.repositories;

import com.example.tucasitatasaciones.dtos.DistrictDTO;
import com.example.tucasitatasaciones.dtos.PropertyDTO;

import java.util.List;

public interface PropertyRepository {
    Boolean checkIfDistrictExistsFor(PropertyDTO property);
}
