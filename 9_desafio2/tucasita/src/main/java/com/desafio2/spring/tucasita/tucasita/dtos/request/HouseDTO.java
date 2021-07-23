package com.desafio2.spring.tucasita.tucasita.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class HouseDTO {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @Pattern(regexp = "^[A-Z][a-z]+", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    private String name;

    @NotNull(message = "El barrio no puede estar vacío.")
    @Valid
    private PriceDTO district;

    @NotEmpty(message = "La lista de cuartos no puede estar vacía.")
    private List<@Valid RoomDTO> rooms;
}
