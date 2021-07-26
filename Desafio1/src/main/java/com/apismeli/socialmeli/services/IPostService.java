package com.apismeli.socialmeli.services;

import com.apismeli.socialmeli.dtos.request.PublicationDTO;
import com.apismeli.socialmeli.dtos.response.PostBySellerDTO;
import org.springframework.http.ResponseEntity;

public interface IPostService {
    ResponseEntity register(PublicationDTO publicationDTO) throws Exception;

    PostBySellerDTO initializer();

    Object showPosts(Integer userId, String order) throws Exception;

}
