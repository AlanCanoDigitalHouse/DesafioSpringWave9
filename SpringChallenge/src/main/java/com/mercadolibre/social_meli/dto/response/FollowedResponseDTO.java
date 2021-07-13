package com.mercadolibre.social_meli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowedResponseDTO {

    private Integer userId;
    private String userName;
    private List<UserResponseDTO> followed;

}
