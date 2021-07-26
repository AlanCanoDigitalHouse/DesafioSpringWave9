package com.example._9desafio2.dtos.response;
import com.example._9desafio2.dtos.request.EnviromentDTO;
import com.example._9desafio2.dtos.request.PropertyDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PropertyResponseDTO {
    private Double squareMeters;
    private Double price;
    private EnviromentDTO biggest;
    private List<EnviromentResponseDTO> enviroments;


    public PropertyResponseDTO(PropertyDTO property) {

    }


}
