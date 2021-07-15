package com.mercadolibre.desafio_spring.dtos.response;

import com.mercadolibre.desafio_spring.entities.FoundingPost;
import com.mercadolibre.desafio_spring.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCampaingByUserResponse {
    Integer userId;
    String userName;
    ArrayList<Post> campaings;
}
