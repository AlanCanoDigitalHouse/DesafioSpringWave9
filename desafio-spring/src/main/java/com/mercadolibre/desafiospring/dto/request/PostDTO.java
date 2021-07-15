package com.mercadolibre.desafiospring.dto.request;

import lombok.*;

@Getter
@Setter
public class PostDTO {
    private int userId;
    private String date;
    private ProductDTO detail;
    private int category;
    private double price;
}
