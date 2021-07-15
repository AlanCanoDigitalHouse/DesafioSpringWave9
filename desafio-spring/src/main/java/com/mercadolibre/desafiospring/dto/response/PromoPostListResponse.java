package com.mercadolibre.desafiospring.dto.response;

import com.mercadolibre.desafiospring.model.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PromoPostListResponse {
    private int userId;
    private String userName;
    private List<PromoPostDTOResponse> posts;

    public PromoPostListResponse(int userId, String userName,
                                 List<Post> promoPosts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = new ArrayList<>();
        promoPosts.forEach( (promoPost) -> {
           this.posts.add( new PromoPostDTOResponse(
                   promoPost.getDate(), promoPost.getProduct(),
                   promoPost.getCategory(), promoPost.getPrice(),
                   promoPost.getDiscount()
           ) );
        });
    }
}
