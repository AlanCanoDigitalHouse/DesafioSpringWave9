package com.mercadolibre.socialmeli.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PostPromoListResponse {
    private Integer userId;
    private String userName;
    private List<PostPromoResponseDTO> posts;
}
