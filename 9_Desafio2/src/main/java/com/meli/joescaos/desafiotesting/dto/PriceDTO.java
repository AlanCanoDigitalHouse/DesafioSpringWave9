package com.meli.joescaos.desafiotesting.dto;

public class PriceDTO{

    private String location;
    private double price;

    public PriceDTO(String location, Integer price){
        this.location = location;
        this.price = price;
    }

    public String getLocation(){
        return location;
    }

    public PriceDTO(){
    }

    public void setLocation(String location){
        this.location = location;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(Integer price){
        this.price = price;
    }
}
