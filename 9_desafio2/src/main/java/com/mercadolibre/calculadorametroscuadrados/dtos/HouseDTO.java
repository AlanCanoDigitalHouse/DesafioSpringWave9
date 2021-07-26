package com.mercadolibre.calculadorametroscuadrados.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class HouseDTO {
    @NotBlank(message = "El nombre de la propiedad no puede estar vacio")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre de la propiedad debe comenzar con mayuscula")
    private String prop_name;
    @Valid
    private DistrictDTO district;
    @Valid
    private List<EnvironmentDTO> environments;
}
