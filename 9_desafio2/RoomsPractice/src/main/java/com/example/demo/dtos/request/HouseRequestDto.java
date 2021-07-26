package com.example.demo.dtos.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class HouseRequestDto {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío")
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]+$", message = "El nombre de la propiedad debe comenzar con mayúscula")
    @Length(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
    private String prop_name;

    @NotBlank(message = "El barrio no puede estar vacío")
    @Length(max = 45, message = "La longitud del nombre no puede superar los 45 caracteres")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío")
    @Min(value = 1, message = "El precio mínimo permitido por metro cuadrado no puede ser menor a 1 U$S")
    @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S")
    private Double district_price;

    @NotEmpty(message = "House must to have at least one room")
    private List<@Valid RoomRequestDto> rooms;


}
