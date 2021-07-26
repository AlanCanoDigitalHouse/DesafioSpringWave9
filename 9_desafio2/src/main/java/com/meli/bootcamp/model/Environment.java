package com.meli.bootcamp.model;

import com.meli.bootcamp.dto.EnvironmentDTO;

public class Environment {
    private String environment_name;
    private Double environment_width;
    private Double environment_length;
    private Double environment_area;

    public Environment(EnvironmentDTO environment){
        this.environment_name = environment.getEnvironment_name();
        this.environment_length = environment.getEnvironment_length();
        this.environment_width = environment.getEnvironment_width();
        this.environment_area=this.environment_length*this.environment_width;
    }

    public String getEnvironment_name() {
        return environment_name;
    }

    public Double getEnvironment_area() {
        return environment_area;
    }
}

