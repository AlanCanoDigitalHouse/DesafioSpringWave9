package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostModel {

    // post
    private int userId;
    private UserModel user;
    private Date date;

    // product
    private int productId;
    private int categoryId;
    private double price;

    public PostModel(int userId, UserModel user, Date date, int productId, int categoryId, double price) {
        this.userId = userId;
        this.user = user;
        this.date = date;
        this.productId = productId;
        this.categoryId = categoryId;
        this.price = price;
    }

    public PostModel(int userId, Date date, int category, double price) {
        this.userId = userId;
        this.date = date;
        this.categoryId = category;
        this.price = price;
    }


}
