package com.meli.desafiospring.DTOs;

import com.meli.desafiospring.models.Product;

import java.util.Date;

public class PostDTO {

    Integer id_post;
    Date date;
    Product detail;
    String category;
    Double price;

}
