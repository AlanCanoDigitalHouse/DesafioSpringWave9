package com.meli.socialmeli.models;

import lombok.Setter;

@Setter
public class Product {
    private int product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    @Override
    public String toString(){
        return "{\n" + "product_id: " + this.product_id +
                "\n" + "productName: " + this.productName +
                "\n" + "type: " + this.type +
                "\n" + "brand: " + this.brand +
                "\n" + "color: " + this.color +
                "\n" + "notes: " + this.notes +
                "\n}";
    }
}
