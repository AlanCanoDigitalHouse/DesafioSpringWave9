package com.mercadolibre.socialmedia.dtos.response;

import com.mercadolibre.socialmedia.dtos.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsUserResponse {
    private Integer userId;
    private List<PostDto> posts;
}
