package com.example.tucasitatasaciones.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnviromentDTO {
    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @Pattern(regexp = "^\\p{Lu}.*$", message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres.", max = 30)
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
