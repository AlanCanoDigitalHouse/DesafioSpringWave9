package com.mercadolibre.socialmeli.dto.response;

import com.mercadolibre.socialmeli.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponseDTO {
    int userId;
    List<Post> posts;
}
