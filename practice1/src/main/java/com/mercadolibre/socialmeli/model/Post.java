package com.mercadolibre.socialmeli.model;

import com.mercadolibre.socialmeli.dto.request.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id_post;
    private Integer userId;
    private LocalDate date;
    private ProductDTO detail;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;
}
