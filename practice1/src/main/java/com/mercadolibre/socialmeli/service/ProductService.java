package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.PostDTO;
import com.mercadolibre.socialmeli.dto.response.PostResponseListDTO;

public interface ProductService {
    PostDTO createPost(PostDTO post);

    PostResponseListDTO findPostFromFollowedUsers(Integer followerId);
}
