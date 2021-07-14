package com.example.desafio_spring.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Data

public class PostRequestDTO {
    private Integer userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private ProductRequestDTO detail;
    private Integer category;
    private Double price;

    public PostRequestDTO(Integer userId, String date, ProductRequestDTO detail, Integer category, Double price) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.userId = userId;
        this.date = format.parse(date);
        this.detail = detail;
        this.category = category;
        this.price = price;
    }
}
