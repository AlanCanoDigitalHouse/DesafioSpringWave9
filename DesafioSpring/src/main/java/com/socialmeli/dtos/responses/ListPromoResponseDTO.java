package com.socialmeli.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListPromoResponseDTO {

    private Integer userId;
    private String userName;

    private List<PromoPostResponseDTO> posts;
}
