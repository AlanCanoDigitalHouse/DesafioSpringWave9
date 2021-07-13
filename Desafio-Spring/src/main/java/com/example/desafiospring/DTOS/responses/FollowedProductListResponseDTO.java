package com.example.desafiospring.DTOS.responses;

import com.example.desafiospring.DTOS.requests.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowedProductListResponseDTO {
        private Integer userId;
        private List<PostResponseDTO> posts;
}
