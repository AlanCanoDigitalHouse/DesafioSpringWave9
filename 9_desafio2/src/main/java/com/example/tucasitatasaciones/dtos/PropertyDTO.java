package com.example.tucasitatasaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
@GroupSequence({PropertyDTO.class, NotBlank.class, Pattern.class, Size.class})
public class PropertyDTO {
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.", groups = NotBlank.class)
    @Pattern(regexp = "^\\p{Lu}.*$", message = "El nombre de la propiedad debe comenzar con mayúscula.", groups = Pattern.class)
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres.", max = 30, groups = Size.class)
    private String prop_name;

    @Valid
    private DistrictDTO district;

    @Valid
    @Size(message = "La propiedad debe tener al menos 1 ambiente.", min = 1)
    private List<EnviromentDTO> enviroments;
}
