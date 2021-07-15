package com.example.demo.dtos;

import com.example.demo.models.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Data
@AllArgsConstructor
@Validated
public class SimplePostDto {

    private int userId;
    private int id_post;
    
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date date;

    private Product detail;
    private int category;
    private double price;
}
