package com.example.tucasitatasaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DistrictDTO {
    @NotNull(message = "El barro no puede estar vacío.")
    @Size(message = "La longitud del barrio no puede superar los 45 caracteres.", max = 45)
    private String district_name;


    private Double district_price;
}
