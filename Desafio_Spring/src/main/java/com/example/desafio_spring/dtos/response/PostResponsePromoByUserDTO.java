package com.example.desafio_spring.dtos.response;

import com.example.desafio_spring.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class PostResponsePromoByUserDTO {
    private Integer userId;
    private String userName;
    private ArrayList<Post> posts;
}
