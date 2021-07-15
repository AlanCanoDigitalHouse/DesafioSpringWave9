package com.example.desafiospringboot.service.impl;

import com.example.desafiospringboot.dao.DaoPost;
import com.example.desafiospringboot.dao.DaoUsuarios;
import com.example.desafiospringboot.dto.UserPost;
import com.example.desafiospringboot.model.Post;
import com.example.desafiospringboot.model.PromoPost;
import com.example.desafiospringboot.service.PostService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private DaoPost daoPost;

    @Autowired
    private DaoUsuarios daoUsuarios;

    @Override
    public boolean newPost(Post toPost) {
        return daoPost.writePost(toPost);
    }

    @Override
    public UserPost postList(int usuario) {
        UserPost payload = new UserPost();
        payload.setUserId(usuario);
        JSONArray posts = daoPost.fetchPosts(usuario);
        List <JSONObject> toSort = new ArrayList<JSONObject>();
        for (int i = 0; i < posts.size(); i++) {
            toSort.add((JSONObject) posts.get(i));
        }
        System.out.println("salida generica : "+toSort);
        Collections.sort( toSort, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject a, JSONObject b) {
                Date aa = new Date();
                Date bb = new Date();
                try {
                    bb = new SimpleDateFormat("dd/MM/yyyy").parse( (String) b.get("date") );
                    aa = new SimpleDateFormat("dd/MM/yyyy").parse( (String) a.get("date"));
                } 
                catch (Exception e) {
                    //do something
                }
                Long result = aa.getTime() - bb.getTime();
                return result.intValue();
            }
        });
        System.out.println("salida ordenada : "+toSort);
        payload.setPosts(posts);
        return payload;
    }

    @Override
    public JSONArray getAllPosts() {
        return daoPost.loadPost();
    }

    @Override
    public boolean newPromoPost(PromoPost toPost) {
        
        return daoPost.writePromoPost(toPost);
    }

    @Override
    public JSONArray postPromo(int userId) {
        return daoPost.loadPromos(userId);
    }

    @Override
    public Boolean idOnUse(int id) {
        
        // TODO Auto-generated method stub
        return daoPost.idOnUse(id);
    }
    
}
