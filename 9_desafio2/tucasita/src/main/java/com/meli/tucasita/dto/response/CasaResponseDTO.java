package com.meli.tucasita.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class CasaResponseDTO {

    @JsonProperty("house_size_meters")
    private Double metrosCuadradosCasa;
    @JsonProperty("house_price")
    private Double valorCasa;
    @JsonProperty("environment")
    private Map<String, Double> habitaciones;
    @JsonProperty("bigger_room")
    private String habitacionMasGrande;
}
