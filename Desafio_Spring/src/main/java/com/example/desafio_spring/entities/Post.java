package com.example.desafio_spring.entities;

import com.example.desafio_spring.dtos.request.ProductRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@Data
@NoArgsConstructor

public class Post {
    private Integer userId;
    private Integer id_post;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private ArrayList<ProductRequestDTO> detail;
    private Integer category;
    private Double price;



    public Post(Integer userId, Integer id_post, Date date, ArrayList<ProductRequestDTO> detail, Integer category, Double price) throws ParseException {
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
