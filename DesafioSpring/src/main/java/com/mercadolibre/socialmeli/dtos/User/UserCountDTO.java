package com.mercadolibre.socialmeli.dtos.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCountDTO {
    private Integer userId;
    private String userName;
    private Integer followers_count;
}
