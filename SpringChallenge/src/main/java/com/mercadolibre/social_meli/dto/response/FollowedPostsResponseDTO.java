package com.mercadolibre.social_meli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FollowedPostsResponseDTO {

    private Integer userId;
    private List<ProductResponseDTO> posts;

    public FollowedPostsResponseDTO() {
        this.posts = new ArrayList<>();
    }
}
