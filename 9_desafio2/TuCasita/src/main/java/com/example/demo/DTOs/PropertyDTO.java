package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    @NotNull(message = "Property name field can not be empty.")
    @Pattern(regexp = "^[A-Z].*",  message ="Property name field should start with a capital letter")
    @Size(min = 1, message = "Property name field can not be empty.")
    @Size(max = 30, message = "roperty name field can not exceed 30 characters.")
    private String prop_name;

    @NotNull(message = "District name field in Property can not be empty.")
    @Size(min = 1, message = "District name field in Property can not be empty.")
    @Size(max = 45, message = "District length in Property can not exceed 45 characters.")
    private String district_name;

    @NotEmpty(message = "Each property must have at least 1 environment.")
    private List<@Valid EnvironmentDTO> environments;

}
