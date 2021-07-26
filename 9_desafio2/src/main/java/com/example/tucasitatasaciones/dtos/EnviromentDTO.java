package com.example.tucasitatasaciones.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@GroupSequence({EnviromentDTO.class, NotBlank.class, Pattern.class, Size.class})
public class EnviromentDTO {
    @NotBlank(message = "El nombre del ambiente no puede estar vacío.", groups = NotBlank.class)
    @Pattern(regexp = "^\\p{Lu}.*$", message = "El nombre del ambiente debe comenzar con mayúscula.", groups = Pattern.class)
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres.", max = 30, groups = Size.class)
    private String enviroment_name;
    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @DecimalMax(message = "El máximo ancho permitido por propiedad es de 25 mts.", value = "25")
    @DecimalMin(message = "El minimo ancho por propiedad debe ser mayor a 0 mts.", value = "0", inclusive = false)
    private Double enviroment_width;
    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @DecimalMax(message = "El máximo largo permitido por propiedad es de 33 mts.", value = "33")
    @DecimalMin(message = "El minimo largo por propiedad debe ser mayor a 0 mts.", value = "0", inclusive = false)
    private Double enviroment_length;
    private Double enviroment_mts2;
}
