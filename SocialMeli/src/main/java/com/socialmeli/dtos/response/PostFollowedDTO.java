package com.socialmeli.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class PostFollowedDTO {

    private Integer userId;
    private ArrayList<PostResponseDTO> posts;

}
