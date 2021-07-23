package com.mercadolibre.tucasitatasaciones.dtos.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mercadolibre.tucasitatasaciones.dtos.req.EnvironmentDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssessmentDTO {

    private String propName;
    private String districtName;

    private Double dimension;
    private Double price;
    private EnvironmentDTO biggestRoom;
    private List<EnvironmentDTO> roomsDimensions;

}
