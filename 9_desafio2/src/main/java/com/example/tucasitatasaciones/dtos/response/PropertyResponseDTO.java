package com.example.tucasitatasaciones.dtos.response;

import com.example.tucasitatasaciones.dtos.EnviromentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyResponseDTO {
    private Double totalMts2;
    private Double totalPrice;
    private EnviromentDTO biggestEnviroment;
    private List<EnviromentDTO> enviroments;
}
