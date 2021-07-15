package com.mercado_libre.bootcamp.spring.desafio.dtos.response;

import com.mercado_libre.bootcamp.spring.desafio.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PromoListResponseDTO {
    private int userId;
    private String userName;
    private List<Post> posts;
}
