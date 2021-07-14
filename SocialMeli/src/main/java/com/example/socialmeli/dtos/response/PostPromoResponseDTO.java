package com.example.socialmeli.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPromoResponseDTO {

    private Integer userId;
    private String userName;
    private Integer promoproducts_count;

}
