package com.mercadolibre.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Comparable<Post> {
    private Integer userId;
    private Integer id_post;
    private LocalDate date;
    private Product detail;
    private Integer category;
    private Double price;


    @Override
    public int compareTo(Post p) {
        return this.getDate().compareTo(p.getDate());
    }
}
