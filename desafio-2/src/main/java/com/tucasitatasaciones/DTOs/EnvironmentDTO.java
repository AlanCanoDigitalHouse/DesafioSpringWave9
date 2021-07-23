package com.tucasitatasaciones.DTOs;

import com.tucasitatasaciones.globalconstants.Message;
import com.tucasitatasaciones.globalconstants.Regex;

import javax.validation.constraints.*;
import java.util.Objects;

public class EnvironmentDTO {

    @NotNull(message = Message.NOT_NULL_ENVIRONMENT_NAME)
    @NotBlank(message = Message.NOT_NULL_ENVIRONMENT_NAME)
    @Size(min = 1, max = 30, message = Message.MAX_LENGTH_ENVIRONMENT_NAME)
    @Pattern(regexp = Regex.SPANISH_SENTENCE, message = Message.PATTERN_ENVIRONMENT_NAME)
    private String environment_name;

    @NotNull(message = Message.NOT_NULL_ENVIRONMENT_WIDTH)
    @DecimalMin(value = "0.1", message = Message.NOT_NULL_ENVIRONMENT_WIDTH)
    @DecimalMax(value = "25.0", message = Message.MAX_WIDTH)
    private double environment_width;

    @NotNull(message = Message.NOT_NULL_ENVIRONMENT_LENGTH)
    @DecimalMin(value = "0.1", message = Message.NOT_NULL_ENVIRONMENT_LENGTH)
    @DecimalMax(value = "33.0", message = Message.MAX_LENGTH)
    private double environment_length;

    public EnvironmentDTO() {
    }

    public EnvironmentDTO(String environment_name, double environment_width, double environment_length) {
        this.environment_name = environment_name;
        this.environment_width = environment_width;
        this.environment_length = environment_length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnvironmentDTO that = (EnvironmentDTO) o;
        return Double.compare(that.environment_width, environment_width) == 0 && Double.compare(that.environment_length, environment_length) == 0 && Objects.equals(environment_name, that.environment_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(environment_name, environment_width, environment_length);
    }

    public String getEnvironment_name() {
        return environment_name;
    }

    public void setEnvironment_name(String environment_name) {
        this.environment_name = environment_name;
    }

    public double getEnvironment_width() {
        return environment_width;
    }

    public void setEnvironment_width(Integer environment_width) {
        this.environment_width = environment_width;
    }

    public double getEnvironment_length() {
        return environment_length;
    }

    public void setEnvironment_length(Integer environment_length) {
        this.environment_length = environment_length;
    }

    public double getSquareFeet() {
        return this.environment_width * this.environment_length;
    }
}
