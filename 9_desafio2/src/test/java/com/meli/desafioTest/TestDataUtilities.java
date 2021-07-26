package com.meli.desafioTest;

import com.meli.desafioTest.Dtos.DistrictDTO;
import com.meli.desafioTest.Dtos.EnvironmentDTO;
import com.meli.desafioTest.Dtos.HouseDTO;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtilities {

    public static HouseDTO getHouseWithAllAndCorrectData() {
        DistrictDTO district = new DistrictDTO("Aguada", 300.0);
        List<EnvironmentDTO> enviroments = new ArrayList<>();
        enviroments.add(new EnvironmentDTO("Dormitorio1", 10.0, 15.0));
        enviroments.add(new EnvironmentDTO("Dormitorio2", 8.0, 3.0));
        enviroments.add(new EnvironmentDTO("DormitorioComedor", 25.0, 13.0));
        return new HouseDTO("Vacaciones", district, enviroments);
    }

    public static HouseDTO getHouseWithNullName() {
        HouseDTO house = getHouseWithAllAndCorrectData();
        house.setProp_name(null);
        return house;
    }

    public static HouseDTO getHouseWithoutDistrictPrice() {
        HouseDTO house = getHouseWithAllAndCorrectData();
        house.getDistrict().setDistrict_price(0.0);
        return house;
    }

    public static HouseDTO getHouseWithBadEnvironmentName(){
        HouseDTO house = getHouseWithAllAndCorrectData();
        house.getEnvironments().stream().findFirst().get().setEnvironment_name("Habitacion con nombre mas largo de treinta caracteres");
        return house;
    }

    public static EnvironmentDTO BiguestRoom(List<EnvironmentDTO> list){
        EnvironmentDTO result = new EnvironmentDTO();
        for (EnvironmentDTO e: list) {
            if(result.getSquareMeters()< e.getSquareMeters())
                    result = e;
        }
        return result;
    }
}
