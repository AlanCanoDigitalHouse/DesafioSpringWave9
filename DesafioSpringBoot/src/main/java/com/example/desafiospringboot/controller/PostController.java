package com.example.desafiospringboot.controller;

import com.example.desafiospringboot.dto.UserPost;
import com.example.desafiospringboot.model.Post;
import com.example.desafiospringboot.model.PromoPost;
import com.example.desafiospringboot.service.PostService;
import com.example.desafiospringboot.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/products/newpost")
    public void postProduct(@RequestBody JSONObject body, HttpServletResponse response) throws IOException{
        System.out.println("user exist... "+userService.usrExist((Integer)body.get("userId"))+"  id on use::::: "+ postService.idOnUse( (Integer)body.get("id_post") ) );
        if(userService.usrExist((Integer)body.get("userId")) && !postService.idOnUse( (Integer)body.get("id_post") ) ){
            Post p = new Post();
            p.asimilar(body);
            postService.newPost(p);
        }else{
            response.sendError(400, "Usuario no existe");
        }
    }
    @GetMapping("/products/followed/{userId}/list")
    public UserPost userPosts(@PathVariable(value = "userId") int userId, HttpServletResponse response)throws IOException{
        if(userService.usrExist(userId)){
            return postService.postList(userId);
        }else{
            response.sendError(400, "Usuario no existe");
            return null;
        }
        
    }
    @PostMapping("/products/newpromopost")
    public void promoPost(@RequestBody JSONObject body, HttpServletResponse response) throws IOException{
        if(userService.usrExist((Integer)body.get("userId"))){
            PromoPost p = new PromoPost();
            p.asimilar(body);
            postService.newPromoPost(p);
        }else{
            response.sendError(400, "Usuario no existe");
        }
    }
    @GetMapping("/products/{userId}/countPromo/")
    public JSONObject promoPostCount(HttpServletResponse response, @PathVariable(value = "userId") int userId) throws IOException{
        if(userService.usrExist(userId)){
            JSONObject ans = new JSONObject();
            ans.put("userId", userId);
            ans.put("userName", userService.getUser(userId).getUserName());
            ans.put("promoproducts_count", postService.postPromo(userId).size());
            return ans;
        }else{
            response.sendError(400, "Usuario no existe");
            return null;
        }
    }
    @GetMapping("/products/{userId}/list/")
    public JSONArray promoPostList(HttpServletResponse response, @PathVariable(value = "userId") int userId) throws IOException{
        if(userService.usrExist(userId)){
            JSONObject ans = new JSONObject();
            ans.put("userId", userId);
            ans.put("userName", userService.getUser(userId).getUserName());
            ans.put("posts", postService.postPromo(userId));
            return postService.postPromo(userId);
        }else{
            response.sendError(400, "Usuario no existe");
            return null;
        }
    }
}
