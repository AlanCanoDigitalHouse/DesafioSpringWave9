package com.example._9desafio2.dtos.response;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EnviromentResponseDTO {
    private String environment_name;
    private Double enviroment_squareMeters;
}
