package com.example.demo.DTOs;

import com.example.demo.Models.ProductModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private int userId;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date date;
    private ProductDTO detail;
    private int category;
    private double price;

}