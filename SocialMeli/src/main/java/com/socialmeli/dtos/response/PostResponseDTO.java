package com.socialmeli.dtos.response;

import com.socialmeli.dtos.request.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostResponseDTO {

    private Integer userId;
    private Integer id_post;
    private String date;
    private ProductRequestDTO detail;
    private Integer category;
    private Double price;

}
