package com.meli.socialmeli.models;

import lombok.Setter;

@Setter
public class Post {
    private int userId;
    private String date;
    private Product detail;
    private int category;
    private double price;

    @Override
    public String toString(){
        return "{\n" + "userId: " + this.userId +
                "\n" + "date: " + this.date +
                "\n" + "detail: " + this.detail.toString() +
                "\n" + "category: " + this.category +
                "\n" + "price: " + this.price +
                "\n}";
    }
}
