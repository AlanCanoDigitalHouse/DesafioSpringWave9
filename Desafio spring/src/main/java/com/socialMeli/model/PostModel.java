package com.socialMeli.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter @NoArgsConstructor
public class PostModel extends AbstractModel {
    private int userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date date;
    private int product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
    private int category;
    private double price;

    public PostModel(int id, int userId, Date date, int product_id, String productName, String type, String brand, String color, String notes, int category, double price) {
        this.setId(id);
        this.userId = userId;
        this.date = date;
        this.product_id = product_id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
        this.category = category;
        this.price = price;
    }

    @Override
    public String getModelClassName() {
        return "post";
    }
}
