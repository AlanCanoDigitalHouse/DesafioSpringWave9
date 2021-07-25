package com.desafio.tucasitatasaciones.util;

import com.desafio.tucasitatasaciones.dto.PropertyRequestDTO;
import com.desafio.tucasitatasaciones.dto.PropertyResponseDTO;
import com.desafio.tucasitatasaciones.model.Environment;

import java.util.ArrayList;
import java.util.List;

public class Request {
    public static PropertyRequestDTO getRequest(){
        Environment env1 = new Environment("Cuarto", 20, 20);
        Environment env2 = new Environment("Cocina", 10, 5);
        List<Environment> envs = new ArrayList<>();
        envs.add(env1);
        envs.add(env2);
        return new PropertyRequestDTO("Hogar Dulce Hogar Dulce Hogar", "Alamos", 5, envs);
    }

    public static PropertyResponseDTO getResponse(){
        return new PropertyResponseDTO(getRequest(), 450, 2250, "Cuarto");
    }
}
