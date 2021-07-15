package com.example.demo.dtos.response;

import com.example.demo.models.Product;
import lombok.Data;

import java.util.Date;

@Data
public class PostResponseDto {

    private int id_post;
    private Date date;
    private Product detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    public PostResponseDto(int id_post, Date date, Product detail, int category, double price, boolean hasPromo, double discount) {
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
}
