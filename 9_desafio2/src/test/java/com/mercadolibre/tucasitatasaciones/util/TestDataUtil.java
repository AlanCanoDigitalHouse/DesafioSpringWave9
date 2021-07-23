package com.mercadolibre.tucasitatasaciones.util;

import com.mercadolibre.tucasitatasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dto.EnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dto.request.PropertyRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {

    public static PropertyRequestDTO getPropertyFromDistrict(String districtName) {
        var district = getDistrict(districtName);
        List<EnvironmentDTO> environments = new ArrayList<>(){{
            add(getEnvironment("testEnv", 10D, 10D));
            add(getEnvironment("testEnv2", 10D, 10D));
            add(getEnvironment("testEnv3", 10D, 10D));
        }};

        return new PropertyRequestDTO("testProp", district, environments);
    }

    public static PropertyRequestDTO getPropertyWithDifferentEnvironments(String districtName) {
        var district = getDistrict(districtName);
        List<EnvironmentDTO> environments = new ArrayList<>(){{
            add(getEnvironment("testEnv", 10D, 10D));
            add(getEnvironment("testEnv2", 10D, 15D));
            add(getEnvironment("testEnv3", 10D, 20D));
        }};

        return new PropertyRequestDTO("testProp", district, environments);
    }

    public static DistrictDTO getDistrict(String name) {
        return new DistrictDTO(name, 100.0);
    }

    public static EnvironmentDTO getEnvironment(String name, Double width, Double length) {
        return new EnvironmentDTO(name, width, length);
    }
}
