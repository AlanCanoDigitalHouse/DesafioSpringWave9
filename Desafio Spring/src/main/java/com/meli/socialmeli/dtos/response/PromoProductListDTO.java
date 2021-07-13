package com.meli.socialmeli.dtos.response;

import com.meli.socialmeli.models.Post;
import com.meli.socialmeli.models.User;

import java.util.List;

public class PromoProductListDTO extends User {
    private List<Post> posts;

    public PromoProductListDTO(User user) {
        super(user);
    }
}
