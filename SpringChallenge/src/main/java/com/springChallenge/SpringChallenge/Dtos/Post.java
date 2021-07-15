package com.springChallenge.SpringChallenge.Dtos;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @NonNull
    private int userId;
    private int id_post;
    @DateTimeFormat
    private Date date;
    @NonNull
    private Product detail;
    @NonNull
    private int category;
    @NonNull
    private double price;
}
