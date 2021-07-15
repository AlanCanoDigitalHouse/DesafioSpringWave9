package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PromoPostDTO {

    private int userId;
    private int id_post;
    private LocalDate date;
    private ProductDTO detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount = 0;
}
