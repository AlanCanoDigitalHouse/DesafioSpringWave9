package com.meli.bootcamp.dto;

import com.meli.bootcamp.model.Environment;
import lombok.*;
@Data
@AllArgsConstructor
public class EnvironmentDetailsDTO {
    private String environment_name;
    private Double environment_area;

    public  EnvironmentDetailsDTO (Environment environment){
        this.environment_name=environment.getEnvironment_name();
        this.environment_area=environment.getEnvironment_area();
    }
}

