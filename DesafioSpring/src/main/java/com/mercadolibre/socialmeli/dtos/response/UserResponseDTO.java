package com.mercadolibre.socialmeli.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Comparable<UserResponseDTO> {
    private Integer userId;
    private String userName;

    @Override
    public int compareTo(UserResponseDTO p) {
        return this.getUserName().compareTo(p.getUserName());
    }
}
