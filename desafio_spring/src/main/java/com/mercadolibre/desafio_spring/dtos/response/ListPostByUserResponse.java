package com.mercadolibre.desafio_spring.dtos.response;

import com.mercadolibre.desafio_spring.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPostByUserResponse {
    int userId;
    ArrayList<Post> posts;
}
