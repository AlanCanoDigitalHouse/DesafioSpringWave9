package com.example.desafiotesting.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PropertyDTO {

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][-a-zA-Z]*(?:\\s+[A-Z][-a-zA-Z]*)?/g")
    String prop_name;

    DistrictDTO district;
}
