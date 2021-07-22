package com.example.desafiotesting.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class EnvironmentDTO {

    @NotBlank
    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][-a-zA-Z]*(?:\\s+[A-Z][-a-zA-Z]*)?/g")
    String environment_name;

    @NotNull
    @DecimalMax(value = "25.0")
    Double environment_width;

    @NotNull
    @DecimalMax(value = "33.0", message = "El máximo largo permitido por propiedad es de 33 mts.")
    Double environment_length;


    public Double calculateSize() {
        return this.getEnvironment_width() * this.getEnvironment_length();
    }
}
