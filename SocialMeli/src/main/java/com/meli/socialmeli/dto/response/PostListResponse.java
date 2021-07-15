package com.meli.socialmeli.dto.response;

import com.meli.socialmeli.dto.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostListResponse {
    private Integer userId;
    private List<PostDTO> posts;

    public PostListResponse(Integer userId, List<PostDTO> posts) {
        this.userId=userId;
        this.posts=posts;
    }
}
