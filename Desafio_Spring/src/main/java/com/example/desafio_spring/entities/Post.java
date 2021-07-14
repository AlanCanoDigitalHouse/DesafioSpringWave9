package com.example.desafio_spring.entities;

import com.example.desafio_spring.dtos.request.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@NoArgsConstructor
public class Post {
    private Integer userId;
    private Integer id_post;
    private Date date;
    private ProductRequestDTO detail;
    private Integer category;
    private Double price;



    public Post(Integer userId, Integer id_post, Date date, ProductRequestDTO detail, Integer category, Double price) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }
    /*
    public void setDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.date = format.parse(date);
    }*/
}
