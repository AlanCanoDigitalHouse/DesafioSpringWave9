package com.desafiospring.socialmeli.dtos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Validated
public class Post implements Comparable<Post> {

    private Integer userId;
    private LocalDate date;
    private ProductDetail detail;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

    @Override
    public int compareTo(Post o) {
        return this.getDate().compareTo(o.getDate());
    }
}
