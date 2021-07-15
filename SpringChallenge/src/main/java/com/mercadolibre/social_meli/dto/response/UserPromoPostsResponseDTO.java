package com.mercadolibre.social_meli.dto.response;

import com.mercadolibre.social_meli.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPromoPostsResponseDTO {

    private Integer userId;
    private String userName;
    private List<Post> posts;

}
