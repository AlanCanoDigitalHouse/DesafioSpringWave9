package com.example.desafiospringboot.service;

import com.example.desafiospringboot.dto.UserPost;
import com.example.desafiospringboot.model.Post;
import com.example.desafiospringboot.model.PromoPost;
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
    public JSONArray getAllPosts();
    public boolean newPromoPost(PromoPost toPost);
    public JSONArray postPromo(int userId);
    public Boolean idOnUse(int id);
}
