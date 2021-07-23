package com.mercadolibre.tucasitatasaciones.dtos.req;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@Validated
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PropertyDTO {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @Pattern(regexp = "^\\p{Lu}.*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    private String propName;

    @Valid
    @NotNull(message = "El districto no puede ser nulo.")
    private DistrictDTO district;

    @Valid
    @NotNull(message = "El districto no puede ser nulo.")
    private List<EnvironmentDTO> environments;

}
