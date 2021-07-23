package com.example.tucasitatasaciones.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnviromentDTO {
    private String enviroment_name;
    private Double enviroment_width;
    private Double enviroment_length;
    private Double enviroment_mts2;
}
