package com.meli.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dtos.request.NewpostDTO;
import com.meli.socialmeli.dtos.response.PostDTO;
import com.meli.socialmeli.models.Post;
import com.meli.socialmeli.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostsRepository implements IPostsRespository{
    private final List<NewpostDTO> postsDatabase;

    public PostsRepository(){
        this.postsDatabase = loadDataBase();
    }

    public List<PostDTO> usersPosts(List<User> users){
        List<Integer> ids = users.stream().map(User::getUserId).collect(Collectors.toList());
        return postsDatabase.stream().filter(p -> ids.contains(p.getUserId())).map(PostDTO::new).collect(Collectors.toList());
    }

    public void addPost(NewpostDTO p){
        this.postsDatabase.add(p);
    }

    public List<NewpostDTO> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:posts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<NewpostDTO>> typeRef = new TypeReference<>() {};
        List<NewpostDTO> posts = null;
        try {
            posts = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }
}
