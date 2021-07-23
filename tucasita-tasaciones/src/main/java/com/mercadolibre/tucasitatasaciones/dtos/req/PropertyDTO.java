package com.mercadolibre.tucasitatasaciones.dtos.req;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mercadolibre.tucasitatasaciones.exception.validations.FirstOrderValidation;
import com.mercadolibre.tucasitatasaciones.exception.validations.SecondOrderValidation;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@Validated
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@GroupSequence({PropertyDTO.class, FirstOrderValidation.class, SecondOrderValidation.class})
public class PropertyDTO {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.", groups = FirstOrderValidation.class)
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.", groups = SecondOrderValidation.class)
    @Pattern(regexp = "^\\p{Lu}.*$", message = "El nombre de la propiedad debe comenzar con mayúscula.", groups = SecondOrderValidation.class)
    private String propName;

    @Valid
    @NotNull(message = "El districto no puede ser nulo.")
    private DistrictDTO district;

    @Valid
    @NotEmpty(message = "La lista de ambientes no puede estar vacia.")
    private List<EnvironmentDTO> environments;

}
