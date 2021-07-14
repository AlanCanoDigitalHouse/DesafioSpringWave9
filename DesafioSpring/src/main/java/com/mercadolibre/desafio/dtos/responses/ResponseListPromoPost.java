package com.mercadolibre.desafio.dtos.responses;

import com.mercadolibre.desafio.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ResponseListPromoPost {

    private Integer userId;
    private String userName;
    private List<Post> posts;
}
