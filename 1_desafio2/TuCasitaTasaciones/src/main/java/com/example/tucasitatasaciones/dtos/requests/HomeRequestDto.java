package com.example.tucasitatasaciones.dtos.requests;

import com.example.tucasitatasaciones.dtos.DistrictDto;
import com.example.tucasitatasaciones.dtos.RoomDto;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class HomeRequestDto {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String name;

    @Valid
    private DistrictDto district;

    @NotEmpty(message = "Lista de habitaciones vacía")
    @Valid
    private List<RoomDto> rooms;

}