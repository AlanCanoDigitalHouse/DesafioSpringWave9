package com.example.desafiotesting.util;

import com.example.desafiotesting.dto.EnvironmentDTO;

import java.util.ArrayList;
import java.util.List;

public class TestUtilGenerator {
    public static List<EnvironmentDTO> getEnvironmentList(){
        List<EnvironmentDTO> list = new ArrayList<>();
        list.add(new EnvironmentDTO("habitación1", 10.0, 10.0));
        list.add(new EnvironmentDTO("habitación2", 10.0, 10.0));
        list.add(new EnvironmentDTO("habitación3", 10.0, 10.0));
        list.add(new EnvironmentDTO("habitación4", 10.0, 10.0));
        list.add(new EnvironmentDTO("habitación5", 10.0, 10.0));
        return list;
    }
}
