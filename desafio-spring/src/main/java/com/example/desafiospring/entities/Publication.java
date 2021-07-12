package com.example.desafiospring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Publication {

    private Long id_post;
    private String date;
    private Product detalle;
    private String category;
    private Double price;
    private boolean hasPromo;
    private Double discount;

}
