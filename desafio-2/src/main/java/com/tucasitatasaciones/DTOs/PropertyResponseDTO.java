package com.tucasitatasaciones.DTOs;

public class PropertyResponseDTO extends PropertyDTO {
    private double squareFeet;
    private double price;
    private EnvironmentDTO biggest;

    public PropertyResponseDTO() {
    }

    public PropertyResponseDTO(PropertyDTO house) {
        this.setProp_name(house.getProp_name());
        this.setEnvironments(house.getEnvironments());
        this.setDistrict_name(house.getDistrict_name());
    }

    public double getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(double squareFeet) {
        this.squareFeet = squareFeet;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EnvironmentDTO getBiggest() {
        return biggest;
    }

    public void setBiggest(EnvironmentDTO biggest) {
        this.biggest = biggest;
    }
}
