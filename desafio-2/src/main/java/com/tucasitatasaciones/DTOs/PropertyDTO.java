package com.tucasitatasaciones.DTOs;

import com.tucasitatasaciones.globalconstants.Message;
import com.tucasitatasaciones.globalconstants.Regex;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class PropertyDTO {

    @NotNull(message = Message.NOT_NULL_PROPERTY_NAME)
    @NotBlank(message = Message.NOT_NULL_PROPERTY_NAME)
    @Size(min = 1, max = 30, message = Message.MAX_LENGTH_PROPERTY_NAME)
    @Pattern(regexp = Regex.SPANISH_NAME, message = Message.PATTERN_PROPERTY_NAME)
    private String prop_name;

    @NotNull(message = Message.NOT_NULL_ENVIRONMENT)
    @NotEmpty(message = Message.NOT_NULL_ENVIRONMENT)
    private List<@Valid EnvironmentDTO> environments;

    @Valid
    private PriceDTO district;

    public PropertyDTO() {
    }

    public PropertyDTO(String name, List<EnvironmentDTO> rooms, PriceDTO district) {
        this.prop_name = name;
        this.environments = rooms;
        this.district = district;
    }

    public PriceDTO getDistrict() {
        return district;
    }

    public void setDistrict(PriceDTO district) {
        this.district = district;
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public List<EnvironmentDTO> getEnvironments() {
        return environments;
    }

    public void setEnvironments(List<EnvironmentDTO> environments) {
        this.environments = environments;
    }
}
