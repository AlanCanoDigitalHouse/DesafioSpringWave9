package com.example.desafiotesting.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class PropertyDTO {

    @NotNull
    @NotBlank
    @Size(max = 30)
    String prop_name;

    DistrictDTO district;

    List<EnvironmentDTO> environments;
}
