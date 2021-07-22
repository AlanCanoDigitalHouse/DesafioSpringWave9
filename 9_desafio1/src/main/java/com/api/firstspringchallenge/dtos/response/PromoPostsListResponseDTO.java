package com.api.firstspringchallenge.dtos.response;

import com.api.firstspringchallenge.models.Post;
import com.api.firstspringchallenge.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PromoPostsListResponseDTO {
    private int userId;
    private String username;
    private List<PostPromoResponseDTO> posts;

    public PromoPostsListResponseDTO(User user, List<PostPromoResponseDTO> posts) {
        this.userId=user.getUserId();
        this.username=user.getUsername();
        this.posts = posts;
    }

}
