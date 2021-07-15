package com.mercado_libre.bootcamp.spring.desafio.dtos.response;

import com.mercado_libre.bootcamp.spring.desafio.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowedProductResponseDTO {
    private int userId;
    private List<Post> posts;
}
