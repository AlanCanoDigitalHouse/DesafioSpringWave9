package com.mercadolibre.socialmeli.dto.response;

import com.mercadolibre.socialmeli.dto.ListUserFollowDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserFollowedResponse {

    private Integer userId;
    private String  userName;
    private List<ListUserFollowDto> followed;
}
