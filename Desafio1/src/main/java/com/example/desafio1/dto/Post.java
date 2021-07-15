package com.example.desafio1.dto;

import com.example.desafio1.exceptions.annotations.UserId;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Post {
    private Integer postId;
    @NotNull
    @UserId
    private Integer userId;

    @NotEmpty
    private String date;

    @Valid
    private Product detail;

    @NotNull
    private Integer category;

    @NotNull
    private Double price;
}
