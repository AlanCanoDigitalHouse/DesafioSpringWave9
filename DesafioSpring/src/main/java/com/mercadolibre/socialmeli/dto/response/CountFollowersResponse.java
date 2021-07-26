package com.mercadolibre.socialmeli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CountFollowersResponse {

    private Integer userId;
    private String  userName;
    private Integer followers_count;

}
