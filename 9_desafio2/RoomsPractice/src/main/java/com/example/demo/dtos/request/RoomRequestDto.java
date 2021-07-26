package com.example.demo.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@Validated
@Getter
@AllArgsConstructor
public class RoomRequestDto {

    @NotBlank(message = "El nombre del ambiente no puede estar vacío")
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]+$", message = "El nombre del ambiente debe comenzar con mayúscula")
    @Length(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
    private String environment_name;

    @NotNull(message = "El largo del ambiente no puede estar vacío")
    @Min(value = 1, message = "El mínimo largo permitido por propiedad es de 1 mts")
    @Max(value = 33, message = "El máximo largo permitido por propiedad es de 33 mts")
    private Double environment_length;

    @NotNull(message = "El ancho del ambiente no puede estar vacío")
    @Min(value = 1, message = "El mínimo ancho permitido por propiedad es de 1 mts")
    @Max(value = 25, message = "El máximo ancho permitido por propiedad es de 25 mts")
    private Double environment_width;

    public Double getArea() {
        return this.getEnvironment_width()* this.getEnvironment_length();
    }

}
