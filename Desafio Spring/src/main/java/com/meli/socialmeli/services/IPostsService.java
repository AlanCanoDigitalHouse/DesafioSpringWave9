package com.meli.socialmeli.services;


import com.meli.socialmeli.dtos.response.PostDTO;

import java.util.List;

public interface IPostsService {
    List<PostDTO> orderDateAsc(List<PostDTO> posts);
    List<PostDTO> orderDateDesc(List<PostDTO> posts);
}
