package com.socialMeli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductsSellersThatUserFollowsDTO {
    private int userId;
    private List<PostInfoResponseDTO> posts;
}
