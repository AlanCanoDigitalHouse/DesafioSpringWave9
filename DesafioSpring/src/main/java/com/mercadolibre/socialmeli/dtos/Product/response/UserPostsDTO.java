package com.mercadolibre.socialmeli.dtos.Product.response;

import com.mercadolibre.socialmeli.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostsDTO {
    private Integer userId;
    private List<PostUserDTO> posts;
}
