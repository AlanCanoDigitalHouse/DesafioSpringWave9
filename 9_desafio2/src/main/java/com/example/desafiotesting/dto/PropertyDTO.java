package com.example.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
public class PropertyDTO {
    @Length(max = 30, message = "Property name is longer than 30")
    @NotEmpty(message = "Property name can't be empty")
    @NotNull(message = "Property name can't be null")
    @Pattern(regexp = "^\\p{Lu}.*$", message = "Property name must start with uppercase")
    private String prop_name;
    @Valid
    @NotNull(message = "District can't be null")
    private DistrictDTO district;
    @Valid
    @NotEmpty(message = "Environtments can't be empty")
    @NotNull(message = "Environments can't be null")
    private List<EnvironmentDTO> environments;
}
