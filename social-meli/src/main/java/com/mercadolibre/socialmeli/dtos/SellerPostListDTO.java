package com.mercadolibre.socialmeli.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SellerPostListDTO {

    private Integer userId;
    private List<PostDTO> posts;

}
