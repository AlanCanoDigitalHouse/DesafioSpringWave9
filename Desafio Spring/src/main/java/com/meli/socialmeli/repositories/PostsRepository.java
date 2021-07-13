package com.meli.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.models.Post;
import com.meli.socialmeli.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class PostsRepository implements IPostsRespository{
    public List<Post> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:posts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Post>> typeRef = new TypeReference<>() {};
        List<Post> posts = null;
        try {
            posts = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }
}
