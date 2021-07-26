package com.mercadolibre.calculadorametroscuadrados.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class UtilPropertyGenerator {

    public static HouseDTO getPropertyWith3Environments(String propName, String district, Double districtPrice) {
        RoomDTO environment1 = new RoomDTO("Environment1", 10D, 10D);
        RoomDTO environment2 = new RoomDTO("Environment2", 20D, 20D);
        RoomDTO environment3 = new RoomDTO("Environment3", 50D, 50D);

        List<RoomDTO> environments = new ArrayList<>();
        environments.add(environment1);
        environments.add(environment2);
        environments.add(environment3);

        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setDistrict_name(district);
        districtDTO.setDistrict_price(districtPrice);

        HouseDTO property = new HouseDTO();
        property.setEnvironments(environments);
        property.setProp_name(propName);
        property.setDistrict(districtDTO);
        return property;
    }

    // Total square feet = 350
    public static HouseDTO getPropertyWith3SmallEnvironments(String propName, String district, Double districtPrice) {
        RoomDTO environment1 = new RoomDTO("Environment1", 5D, 5D);
        RoomDTO environment2 = new RoomDTO("Environment2", 10D, 10D);
        RoomDTO environment3 = new RoomDTO("Environment3", 15D, 15D);

        List<RoomDTO> environments = new ArrayList<>();
        environments.add(environment1);
        environments.add(environment2);
        environments.add(environment3);

        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setDistrict_name(district);
        districtDTO.setDistrict_price(districtPrice);

        HouseDTO property = new HouseDTO();
        property.setEnvironments(environments);
        property.setProp_name(propName);
        property.setDistrict(districtDTO);
        return property;
    }

    public static HouseDTO getPropertyWith3EnvironmentsSameDetails(String propName,
                                                                   String district,
                                                                   Double districtPrice,
                                                                   double environmentWidth,
                                                                   double environmentLength) {
        RoomDTO environment1 = new RoomDTO("Environment1", environmentWidth, environmentLength);
        RoomDTO environment2 = new RoomDTO("Environment2", environmentWidth, environmentLength);
        RoomDTO environment3 = new RoomDTO("Environment3", environmentWidth, environmentLength);

        List<RoomDTO> environments = new ArrayList<>();
        environments.add(environment1);
        environments.add(environment2);
        environments.add(environment3);

        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setDistrict_name(district);
        districtDTO.setDistrict_price(districtPrice);

        HouseDTO property = new HouseDTO();
        property.setEnvironments(environments);
        property.setProp_name(propName);
        property.setDistrict(districtDTO);
        return property;
    }
}
