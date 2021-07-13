package com.mercadolibre.socialmeli.dto.respons;


import com.mercadolibre.socialmeli.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowersListResponse extends User {
    private List<User> followers;
}
