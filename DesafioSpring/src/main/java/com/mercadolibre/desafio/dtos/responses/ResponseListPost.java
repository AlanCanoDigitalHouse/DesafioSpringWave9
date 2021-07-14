package com.mercadolibre.desafio.dtos.responses;

import com.mercadolibre.desafio.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseListPost {

    private Integer userId;
    private List<Post> posts;
}
