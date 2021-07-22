package com.tucasitatasaciones.DTOs;

public class EnvironmentDTO {
    private String environment_name;
    private double environment_width;
    private double environment_length;

    public EnvironmentDTO() {
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
        double result = 0;
        return this.environment_width * this.environment_length;
    }
}
