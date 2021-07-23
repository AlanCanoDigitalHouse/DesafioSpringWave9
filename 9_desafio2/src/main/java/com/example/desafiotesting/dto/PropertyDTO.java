package com.example.desafiotesting.dto;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
public class PropertyDTO {

    @NotNull
    @NotBlank
    @Size(max = 30)
    String prop_name;

    @NotNull
    @Valid
    DistrictDTO district;

    @NotEmpty
    @Valid
    List<EnvironmentDTO> environments;
}
