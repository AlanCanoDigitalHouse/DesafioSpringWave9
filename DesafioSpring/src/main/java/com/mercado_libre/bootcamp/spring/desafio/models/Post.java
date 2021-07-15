package com.mercado_libre.bootcamp.spring.desafio.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Post {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private Product detail;
    private Integer category;
    private double price;
    private boolean hasPromo;
    private double discount;
}
