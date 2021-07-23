package com.mercadolibre.calculadorametroscuadrados.utils;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class TestUtilGenerator {

    public static HouseDTO getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85() {

        RoomDTO env1 = new RoomDTO("Living", null, 6.0, 10.0);
        RoomDTO env2 = new RoomDTO("Living", null, 4.0, 4.0);
        RoomDTO env3 = new RoomDTO("Living", null, 3.0, 3.0);
        List<RoomDTO> envs = new ArrayList<>();
        envs.add(env1);
        envs.add(env2);
        envs.add(env3);
        return new HouseDTO("Prop", "Palermo", 1000.0, envs, null, null, null);
    }

    public static HouseDTO getHouseToResponseWith3EnvLivingBiggestTotalPrice85000TotalMeter85() {
        RoomDTO env1 = new RoomDTO("Living", 60.0, 6.0, 10.0);
        RoomDTO env2 = new RoomDTO("Living", 16.0, 4.0, 4.0);
        RoomDTO env3 = new RoomDTO("Living", 9.0, 3.0, 3.0);
        List<RoomDTO> envs = new ArrayList<>();
        envs.add(env1);
        envs.add(env2);
        envs.add(env3);
        return new HouseDTO("Prop", "Palermo", 1000.0, envs, 85.0, 85000.0, env1);

    }
}
