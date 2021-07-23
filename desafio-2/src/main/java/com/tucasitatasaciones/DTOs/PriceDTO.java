package com.tucasitatasaciones.DTOs;

import com.tucasitatasaciones.globalconstants.Message;

import javax.validation.constraints.*;
import java.util.Objects;

public class PriceDTO {

    @NotNull(message = Message.NOT_NULL_DISTRICT_NAME)
    @NotBlank(message = Message.NOT_NULL_DISTRICT_NAME)
    @Size(min = 1, max = 45, message = Message.MAX_LENGTH_DISTRICT_NAME)
    private String district_name;

    @NotNull(message = Message.NOT_NULL_DISTRICT_PRICE)
    @DecimalMin(value = "0.1", message = Message.NOT_NULL_DISTRICT_PRICE)
    @DecimalMax(value = "4000.0", message = Message.MAX_PRICE)
    private double district_price;

    public PriceDTO(String location, double price) {
        this.district_name = location;
        this.district_price = price;
    }

    public PriceDTO() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDTO priceDTO = (PriceDTO) o;
        return Double.compare(priceDTO.district_price, district_price) == 0 && Objects.equals(district_name, priceDTO.district_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(district_name, district_price);
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public double getDistrict_price() {
        return district_price;
    }

    public void setDistrict_price(double district_price) {
        this.district_price = district_price;
    }
}
