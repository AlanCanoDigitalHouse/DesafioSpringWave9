package com.socialMeli.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostModel extends AbstractModel {
    private Date date;
    private int product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
    private int category;
    private double price;

    @Override
    public String getModelClassName() {
        return "post";
    }
}
