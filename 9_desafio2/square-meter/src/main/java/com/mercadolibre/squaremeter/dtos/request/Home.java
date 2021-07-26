package com.mercadolibre.squaremeter.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Home {
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre de la propiedad no puede superar los 30 caracteres.")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre de la propiedad debe comenzar con mayúscula")
    private String name;
    @Valid
    @NotNull(message= "El barrio es requerido")
    private District district;
    @Valid
    @NotEmpty(message="La propiedad debe contar con al menos una habitacion")
    private List<Environment> environment;

}
