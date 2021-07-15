package com.mercadolibre.socialmeli.dtos.Product.response;

import com.mercadolibre.socialmeli.models.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserPromoPostDTO {
    private Integer userId;
    private String userName;
    private List<Post> posts;

    public UserPromoPostDTO(Integer userId, String userName, List<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
    }
}
