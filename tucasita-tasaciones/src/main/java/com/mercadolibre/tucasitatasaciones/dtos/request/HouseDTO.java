package com.mercadolibre.tucasitatasaciones.dtos.request;

import com.mercadolibre.tucasitatasaciones.validations.FirstValidation;
import com.mercadolibre.tucasitatasaciones.validations.SecondValidation;
import com.mercadolibre.tucasitatasaciones.validations.ThirdValidation;
import lombok.*;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@GroupSequence({HouseDTO.class, FirstValidation.class, SecondValidation.class, ThirdValidation.class})
public class HouseDTO {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío", groups = FirstValidation.class)
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres", max = 30, groups = SecondValidation.class)
    @Pattern(regexp="([A-Z]).+", message = "El nombre de la propiedad debe comenzar con mayúscula.", groups = ThirdValidation.class)
    private String prop_name;

    @NotNull(message = "Debe enviar el barrio donde esta ubicada la casa")
    @Valid
    private DistrictDTO district;

    @NotEmpty(message = "La casa debe tener al menos una habitacion")
    @Valid
    private List<EnvironmentDTO> environments;

}
