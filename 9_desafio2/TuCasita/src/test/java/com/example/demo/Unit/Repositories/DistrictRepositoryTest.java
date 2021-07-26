package com.example.demo.Unit.Repositories;

import com.example.demo.Models.District;
import com.example.demo.Repositories.DistrictRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DistrictRepositoryTest {

    @Test
    public void shouldReturnDistrictNameWhenFindOne() {
        String districtName = "Boadway";
        District district = new District(districtName, 22.22);
        DistrictRepository districtRepository = new DistrictRepository();
        List<District> districts = new ArrayList<>();
        districts.add(district);
        districtRepository.setDistricts(districts);
        District received = districtRepository.findDistrictByName(districtName);
        Assertions.assertEquals(district, received);
    }

    @Test
    public void shouldReturnNullWhenDistrictNameNotFound() {
        String districtName = "Boadway";
        DistrictRepository districtRepository = new DistrictRepository();
        District received = districtRepository.findDistrictByName(districtName);
        Assertions.assertEquals(null, received);
    }

}