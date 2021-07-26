package com.meli.desafioTest.repository;

import com.meli.desafioTest.Dtos.DistrictDTO;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class Repository implements IRepository {
    private final static List<DistrictDTO> DISTRICTS = initDistricts();

    @Override
    public DistrictDTO getDistrictByName(String districtName) {
        return DISTRICTS.stream().filter(d -> d.getDistrict_name().equals(districtName)).findFirst().orElse(null);
    }


    private static List<DistrictDTO> initDistricts() {
        List<DistrictDTO> result = new ArrayList<>();
        result.add(new DistrictDTO("Aguada", 300.0));
        result.add(new DistrictDTO("Cordon", 500.0));
        result.add(new DistrictDTO("Pocitos", 650.0));
        return result;
    }


}
