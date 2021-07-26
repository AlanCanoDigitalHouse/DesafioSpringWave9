package com.example.desafiotesting.util;

import com.example.desafiotesting.dto.DistrictDTO;
import com.example.desafiotesting.dto.EnvironmentDTO;
import com.example.desafiotesting.dto.PropertyDTO;

import java.util.ArrayList;
import java.util.List;

public class TestUtilGenerator {
    public static List<EnvironmentDTO> getEnvironmentList(){
        List<EnvironmentDTO> list = new ArrayList<>();
        list.add(new EnvironmentDTO("habitación1", 25.0, 33.0));
        list.add(new EnvironmentDTO("habitación2", 25.0, 33.0));
        list.add(new EnvironmentDTO("habitación3", 25.0, 33.0));
        list.add(new EnvironmentDTO("habitación4", 25.0, 33.0));
        list.add(new EnvironmentDTO("habitación5", 30.0, 33.0));
        return list;
    }

    public static List<EnvironmentDTO> getEnvironmentListWithOneItem(){
        List<EnvironmentDTO> list = new ArrayList<>();
        list.add(new EnvironmentDTO("habitación1", 25.0, 33.0));
        return list;
    }

    public static PropertyDTO getProperty() {
        List<EnvironmentDTO> environments = TestUtilGenerator.getEnvironmentList();
        DistrictDTO district = new DistrictDTO("Recoleta", 1000.0);
        return new PropertyDTO("casa", district, environments);
    }
}
