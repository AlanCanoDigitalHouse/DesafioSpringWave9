package com.jbianchini.meli.socialmeli.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Post {
    private Integer postId;
    private User user;
    private LocalDate date;
    private List<Product> products;
    private String category;
    private double price;
}
