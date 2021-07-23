package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentDTO {

    @NotNull(message = "Environment name field can not be empty.")
    @Size(min = 1, message = "Environment name field can not be empty.")
    @Pattern(regexp = "^[A-Z].*",  message ="Environment name field must begin with a capital letter.")
    @Size(max = 30, message = "Environment name field cannot exceed 30 characters.")
    private String environment_name;

    @NotNull(message = "Environment width field can not be empty.")
    @DecimalMax(value= "25.0", message = "The maximum width allowed per environment is 25 meters.")
    @DecimalMin(value= "0.1", message = "The minimum width allowed per environment is 0.1 meters.")
    private double environment_width;

    @NotNull(message = "Environment length field can not be empty.")
    @DecimalMax(value= "33.0", message = "The maximum length allowed per environment is 33 meters.")
    @DecimalMin(value= "0.1", message = "The minimum length allowed per environment is 0.1 meters.")
    private double environment_length;

}
