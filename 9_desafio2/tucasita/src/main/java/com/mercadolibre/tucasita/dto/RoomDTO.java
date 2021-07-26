package com.mercadolibre.tucasita.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    @JsonProperty("environment_name")
    private String name;
    @JsonProperty("environment_size")
    private double size;
}
