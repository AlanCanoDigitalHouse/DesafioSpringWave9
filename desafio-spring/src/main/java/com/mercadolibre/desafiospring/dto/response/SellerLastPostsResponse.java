package com.mercadolibre.desafiospring.dto.response;

import com.mercadolibre.desafiospring.model.Post;
import lombok.*;
import java.util.*;

@Getter
@Setter
public class SellerLastPostsResponse {
    private int userId;
    private List<PostDTOResponse> posts;

    public SellerLastPostsResponse(int userId, List<Post> sellerPosts) {
        this.userId = userId;
        this.posts = new ArrayList<>();

        sellerPosts.forEach((post) -> {
            this.posts.add(new PostDTOResponse(post));
        });
    }
}
