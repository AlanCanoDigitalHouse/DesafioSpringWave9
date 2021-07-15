package com.example.demo.respositories;

import com.example.demo.dtos.request.Post;
import com.example.demo.dtos.response.UserDto;
import com.example.demo.exceptions.DuplicateUser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class PostImpl implements SocialMedia{

    private List<Post> posts;
    private static final String PATH_POSTS = System.getProperty("user.dir") + "/src/main/resources/static/posts.json";

    public PostImpl(List<Post> posts) {
        this.posts = loadDatabase();
    }

    @Override
    public List<Post> loadDatabase() {
        File file = null;
        try{
            file = ResourceUtils.getFile(PATH_POSTS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    @Override
    public List<Post> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        TypeReference<List<Post>> typeReference = new TypeReference<>(){};
        List<Post> posts = null;
        try {
            posts = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return posts;
    }

}
