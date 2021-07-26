package com.example.desafio2.utils;

import com.example.desafio2.dtos.EnvDTO;
import com.example.desafio2.dtos.HouseDTO;

import java.util.ArrayList;
import java.util.List;

public class TestGenerator {
    public static HouseDTO generateHouse(){
        HouseDTO house = new HouseDTO();
        house.setDistrict_price(300);
        house.setDistrict_name("Sur");
        house.setProp_name("La Casita");
        List<EnvDTO> envs = new ArrayList<>();
        var env1 = new EnvDTO("Baño",2,4);
        var env2 = new EnvDTO("Habitacion Matrimonial",10,6);
        var env3 = new EnvDTO("Habitacion Visitas",3,4);
        envs.add(env1);
        envs.add(env2);
        envs.add(env3);
        house.setEnvironments(envs);
        return house;
    }


}
