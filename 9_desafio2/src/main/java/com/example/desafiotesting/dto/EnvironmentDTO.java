package com.example.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class EnvironmentDTO {
    @Length(max = 30, message = "Environment name can't be longer than 45 characters")
    @NotEmpty(message = "Environment name can't be empty")
    @NotNull(message = "Environment name can't be null")
    @Pattern(regexp = "^\\p{Lu}.*$", message = "Environment name must start with uppercase")
    private String environment_name;
    @DecimalMin(value = "0.0", inclusive = false, message = "Environment width can't be zero")
    @DecimalMax(value = "25.0", message = "The maximum environment width allowed per property is 25 mts")
    @NotNull(message = "Environment width can't be null")
    private Double environment_width;
    @DecimalMin(value = "0.0", inclusive = false, message = "Environment length can't be zero")
    @DecimalMax(value = "33.0", message = "The maximum environment length allowed per property is 25 mts")
    @NotNull(message = "Environment length is null")
    private Double environment_length;

    public double getSquareMetter() {
        double result = 0;
        result = this.environment_length * this.environment_width;
        return result;
    }
}
