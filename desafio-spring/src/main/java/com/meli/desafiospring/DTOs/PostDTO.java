package com.meli.desafiospring.DTOs;

import com.meli.desafiospring.models.Product;
import lombok.Data;
import lombok.Getter;

import java.util.Calendar;

@Data
@Getter
public class PostDTO {

    Integer id_post;
    Calendar date;
    Product detail;
    String category;
    Double price;

    public PostDTO(Integer id_post, Calendar date, Product detail, String category, Double price) {
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }


}
