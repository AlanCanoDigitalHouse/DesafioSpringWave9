package com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

@Data
@Validated
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDto {


    @NotNull(message = "El nombre del ambiente no puede ser nulo.")
    @NotBlank(message = "El nombre del ambiente no puede estar vacio")
    @Pattern(message = "El nombre del ambiente debe comenzar con mayúscula.", regexp = "^[A-Z].*")
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres.", max= 30)
    private String environment_name;
    @DecimalMin(message = "El minimo largo permitido por propiedad es de 0.5 mts.", value = "0.5")
    @Max(message = "El máximo largo permitido por propiedad es de 33 mts.", value = 33)
    private Double length;
    @DecimalMin(message = "El minimo ancho permitido por propiedad es de 0.5 mts.", value = "0.5")
    @Max(message = "El máximo ancho permitido por propiedad es de 25 mts.", value = 25)
    private Double width;

}

