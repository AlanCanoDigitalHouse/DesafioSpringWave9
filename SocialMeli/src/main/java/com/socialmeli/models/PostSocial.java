package com.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSocial {

    private Integer userId;
    private Integer id;
    private LocalDate date;
    private ProductSocial detail;
    private Integer category;
    private Double price;

}
