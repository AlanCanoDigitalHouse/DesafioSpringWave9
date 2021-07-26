package com.example.tucasitatasaciones.dtos.response;

import com.example.tucasitatasaciones.dtos.EnviromentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class PropertyResponseDTO {
    private Double totalMts2;
    private Double totalPrice;
    private EnviromentDTO biggestEnviroment;
    private List<EnviromentDTO> enviroments;
}
