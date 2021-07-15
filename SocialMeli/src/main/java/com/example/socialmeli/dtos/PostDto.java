package com.example.socialmeli.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Integer userId;
    private Integer id_post;
    private LocalDate date;
    private Integer category;
    private Double price;
    private ProductDto detail;
    private boolean hasPromo;
    private Double discount;
}
