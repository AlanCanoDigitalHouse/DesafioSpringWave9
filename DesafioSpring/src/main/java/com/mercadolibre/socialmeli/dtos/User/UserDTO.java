package com.mercadolibre.socialmeli.dtos.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Comparable<UserDTO> {
    private Integer userId;
    private String userName;

    @Override
    public int compareTo(UserDTO o) {
        return o.getUserName().compareTo(getUserName());
    }
}
