package com.example.desafiospringboot.service;

import com.example.desafiospringboot.dto.UserPost;
import com.example.desafiospringboot.model.Post;
import com.example.desafiospringboot.model.Usuario;

import org.json.simple.JSONArray;

public interface PostService {
    public boolean newPost(Post toPost);
    /**
     *
     * @param usuario
     * @return
     * lista de post de seguidos con usuario seguidor
     * como parametro
     */
    public UserPost postList(int usuario);
}


