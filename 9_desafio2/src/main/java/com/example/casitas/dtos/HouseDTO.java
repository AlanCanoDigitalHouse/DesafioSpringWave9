package com.example.casitas.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Validated
public class HouseDTO {

    @JsonProperty("prop_name")
    @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String propertyName;

    @JsonProperty("district_name")
    @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String districtName;

    @JsonProperty("district_price")
    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @Max(value = 4000,message = "El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S")
    private Double districtPrice;

    private List<EnvironmentDTO> environments;

}
