package com.tucasitatasaciones.DTOs;

import java.util.Objects;

public class PropertyResponseDTO extends PropertyDTO {
    private double squareFeet;
    private double price;
    private EnvironmentDTO biggest;

    public PropertyResponseDTO() {
    }

    public PropertyResponseDTO(PropertyDTO house) {
        this.setProp_name(house.getProp_name());
        this.setEnvironments(house.getEnvironments());
        this.setDistrict(house.getDistrict());
    }

    public double getSquareFeet() {
        return squareFeet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyResponseDTO that = (PropertyResponseDTO) o;
        return Double.compare(that.squareFeet, squareFeet) == 0 && Double.compare(that.price, price) == 0 && biggest.equals(that.biggest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squareFeet, price, biggest);
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
