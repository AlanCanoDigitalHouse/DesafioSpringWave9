package com.meli.socialmeli.models;

import lombok.Setter;

@Setter
public class Post {
    private int userId;
    private int id_post;
    private String date;
    private Product detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    @Override
    public String toString(){
        return "{\n" + "userId: " + this.userId +
                "\n" + "id_post: " + this.id_post +
                "\n" + "date: " + this.date +
                "\n" + "detail: " + this.detail.toString() +
                "\n" + "category: " + this.category +
                "\n" + "price: " + this.price +
                "\n" + "hasPromo: " + this.hasPromo +
                "\n" + "discount: " + this.discount +
                "\n}";
    }
}
