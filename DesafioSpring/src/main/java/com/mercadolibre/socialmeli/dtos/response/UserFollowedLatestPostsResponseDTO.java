package com.mercadolibre.socialmeli.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowedLatestPostsResponseDTO {

    private Integer userId;
    private List<PostResponseDTO> posts = new ArrayList<>();

}
