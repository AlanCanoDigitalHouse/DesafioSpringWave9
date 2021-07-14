package com.example.desafiospring.DTOS.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PromoProductListResponseDTO {
        private Integer userId;
        private String userName;
        private List<PromoPostResponseDTO> posts;
}
