package com.tucasitatasaciones.DTOs;

public class PriceDTO {

    private String district_name;
    private Integer district_price;

    public PriceDTO(String location, Integer price) {
        this.district_name = location;
        this.district_price = price;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public PriceDTO() {
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public Integer getDistrict_price() {
        return district_price;
    }

    public void setDistrict_price(Integer district_price) {
        this.district_price = district_price;
    }
}
