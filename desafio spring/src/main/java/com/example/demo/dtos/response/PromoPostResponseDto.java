package com.example.demo.dtos.response;

import com.example.demo.models.Product;

import java.util.Date;

public class PromoPostResponseDto extends PostResponseDto {

    private boolean hasPromo;
    private double discount;

    public PromoPostResponseDto(int id_post, Date date, Product detail, int category, double price, boolean hasPromo, double discount, boolean hasPromo1, double discount1) {
        super(id_post, date, detail, category, price, hasPromo, discount);
        this.hasPromo = hasPromo1;
        this.discount = discount1;
    }
}
