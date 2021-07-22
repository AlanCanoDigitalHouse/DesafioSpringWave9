package com.mercadolibre.calculadorametroscuadrados.dto;

public class PriceDTO{

    private String location;
    private Integer price;

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

    public Integer getPrice(){
        return price;
    }

    public void setPrice(Integer price){
        this.price = price;
    }
}
