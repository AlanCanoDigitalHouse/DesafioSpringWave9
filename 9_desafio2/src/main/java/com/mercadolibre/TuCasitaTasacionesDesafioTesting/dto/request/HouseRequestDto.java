package com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@Validated
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HouseRequestDto {

    @NotNull(message = "El nombre de la propiedad no puede ser nulo.")
    @NotBlank(message = "El nombre de la casa no puede estar vacio")
    @Pattern(message = "El nombre de la propiedad debe comenzar con mayúscula.", regexp = "^[A-Z].*")
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres.", max= 30)
    private String prop_name;
    @NotNull(message = "El barrio no puede ser nulo.")
    @NotBlank(message = "El nombre del barrio no puede estar vacio")
    @Pattern(message = "El nombre del barrio debe comenzar con mayúscula.", regexp = "^[A-Z].*")
    @Size(message = "La longitud del barrio no puede superar los 45 caracteres.", max= 45)
    private String district_name;
    private ArrayList<@Valid RoomRequestDto> rooms = new ArrayList<>();
}

