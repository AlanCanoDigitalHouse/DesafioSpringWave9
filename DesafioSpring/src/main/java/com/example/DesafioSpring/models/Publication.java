package com.example.DesafioSpring.models;

import lombok.Data;

import java.time.LocalDate;

@Data

public class Publication {

    private Integer userId;
    private Integer id_post;
    private LocalDate date;
    private Integer productID;
    private Integer category;
    private Double price;

}
