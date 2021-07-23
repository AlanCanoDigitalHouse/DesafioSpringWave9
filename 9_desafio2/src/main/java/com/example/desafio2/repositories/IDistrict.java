package com.example.desafio2.repositories;

import com.example.desafio2.dtos.DistrictDTO;

public interface IDistrict {
    DistrictDTO getByName(String name);
}
