package com.tucasitatasaciones.DTOs;

import java.util.List;

public class PropertyDTO {
    private String prop_name;
    private List<EnvironmentDTO> environments;
    private String district_name;

    public PropertyDTO() {

    }

    public PropertyDTO(String name, List<EnvironmentDTO> rooms, String location) {
        this.prop_name = name;
        this.environments = rooms;
        this.district_name = location;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
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
