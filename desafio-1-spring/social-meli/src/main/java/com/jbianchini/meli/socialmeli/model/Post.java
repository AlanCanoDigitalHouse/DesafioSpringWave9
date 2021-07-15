package com.jbianchini.meli.socialmeli.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Post {
    private Integer postId;
    private User user;
    private LocalDate date;
    private Product product;
    private int category;
    private double price;

    public Post(User user, LocalDate date, Product product, int category, double price) {
        this.user = user;
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
    }

//    @Override
//    public int compareTo(Post o) {
//        return this.getDate().isAfter(o.getDate()) ? 1 : -1;
//    }
}
