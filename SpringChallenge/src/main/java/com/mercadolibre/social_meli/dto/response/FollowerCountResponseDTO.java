package com.mercadolibre.social_meli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowerCountResponseDTO {

    private Integer userId;
    private String userName;
    private Integer followers_count;

}
