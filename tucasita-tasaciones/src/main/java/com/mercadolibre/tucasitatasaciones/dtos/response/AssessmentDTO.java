package com.mercadolibre.tucasitatasaciones.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolibre.tucasitatasaciones.dtos.request.EnvironmentDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssessmentDTO {

    private String prop_name;
    private Double squareMeters;
    private Double propertyPrice;
    private EnvironmentDTO biggestRoom;
    private List<EnvironmentDTO> environments;

}
