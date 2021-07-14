package com.mercadolibre.socialmeli.dto.respons;

import com.mercadolibre.socialmeli.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowersResponse extends User {
    private int followers_count;

}
