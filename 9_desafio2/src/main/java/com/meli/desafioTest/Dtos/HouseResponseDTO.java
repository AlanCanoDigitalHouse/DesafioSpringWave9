package com.meli.desafioTest.Dtos;

public class HouseResponseDTO extends HouseDTO{
    private Double squareMeters;
    private Double price;
    private EnvironmentDTO biggest;

    public HouseResponseDTO(HouseDTO house) {
        this.setProp_name(house.getProp_name());
        this.setDistrict(house.getDistrict());
        this.setEnvironments(house.getEnvironments());
    }

    public Double getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(Double squareMeters) {
        this.squareMeters = squareMeters;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public EnvironmentDTO getBiggest() {
        return biggest;
    }

    public void setBiggest(EnvironmentDTO biggest) {
        this.biggest = biggest;
    }
}

