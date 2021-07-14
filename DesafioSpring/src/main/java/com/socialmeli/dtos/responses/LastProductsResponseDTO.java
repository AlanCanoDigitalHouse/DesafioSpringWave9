package com.socialmeli.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LastProductsResponseDTO {

    private Integer userId;

    private List<PostResponseDTO> posts;

}
