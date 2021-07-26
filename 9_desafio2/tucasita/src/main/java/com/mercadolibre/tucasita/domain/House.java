package com.mercadolibre.tucasita.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class House {

    @JsonProperty("prop_name")
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp = "^[A-Z]\\w*", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(min = 1, max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String name;

    @JsonProperty("prop_district")
    @NotNull(message = "El barrio no puede estar vacío.")
    private @Valid District distric;

    @JsonProperty("prop_environments")
    @NotNull(message = "La casa debe tener habitaciones.")
    @Size(min = 1, message = "La casa debe tener almenos una habitación.")
    private List<@Valid Room> rooms;
}
