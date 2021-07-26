package com.meli.bootcamp.dto.request;

import com.meli.bootcamp.dto.DistrictDTO;
import com.meli.bootcamp.dto.EnvironmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@Data
@AllArgsConstructor
@Builder
public class PropertyDTO {

    @NotEmpty(message = "El nombre de la propiedad no puede estar vacío")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre de la propiedad debe comenzar con mayúscula")
    @Length(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
    private String prop_name;

    @NotNull(message = "La propiedad debe estar ubicada en un districto")
    @Valid
    private DistrictDTO district;

    @NotEmpty(message = "La propiedad debe tener al menos un ambiente")
    private @Valid List<EnvironmentDTO> environments;
}

