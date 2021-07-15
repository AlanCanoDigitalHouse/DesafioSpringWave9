package com.api.firstspringchallenge.models;

import com.api.firstspringchallenge.enumerates.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
public class Post {

    private Date date;
    private Product detail;
    private Category category;
    private Double price;
    private boolean hasPromo;
    private double discount;

    public Post(Date date, Product detail, Category category, Double price) {
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public String getProductName(){
        return detail.getProductName();
    }

}
