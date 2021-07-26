package com.meli.desafioTest.repository;

import com.meli.desafioTest.Dtos.DistrictDTO;

public interface IRepository {
    DistrictDTO getDistrictByName(String districtName);
}
