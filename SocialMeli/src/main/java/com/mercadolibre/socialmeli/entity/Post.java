package com.mercadolibre.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Post {
    int id_post;
    Date date;
    Product product;
    int category;
    double price;
}
