package com.mercadolibre.socialmeli.dtos.posts.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostListResponseDTO {

    private Integer userId;
    private List<PostResponseDTO> posts;

}
