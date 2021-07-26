package com.example.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceDTO{

    @NotNull(message = "El barrio no puede estar vacío.")
    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(min =1 , max=45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @NotBlank(message = "El precio de un barrio no puede estar vacío.")
    @Range(min=1, max=4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Integer district_price;


}
