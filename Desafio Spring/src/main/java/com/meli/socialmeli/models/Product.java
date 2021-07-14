package com.meli.socialmeli.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    @Override
    public String toString(){
        return "{\n" + "productName: " + this.productName +
                "\n" + "type: " + this.type +
                "\n" + "brand: " + this.brand +
                "\n" + "color: " + this.color +
                "\n" + "notes: " + this.notes +
                "\n}";
    }
}
