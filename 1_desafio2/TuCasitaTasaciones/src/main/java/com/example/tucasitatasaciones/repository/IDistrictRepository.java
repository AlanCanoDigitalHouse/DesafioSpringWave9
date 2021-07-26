package com.example.tucasitatasaciones.repository;

import com.example.tucasitatasaciones.dtos.DistrictDto;
import com.example.tucasitatasaciones.exceptions.GetDistrictException;

public interface IDistrictRepository {
    DistrictDto findDistrict(String name, Double price);
}
