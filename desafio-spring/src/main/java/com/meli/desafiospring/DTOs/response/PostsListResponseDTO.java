package com.meli.desafiospring.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsListResponseDTO {

    Integer userId;
    List<PostResponseDTO> posts;

}
