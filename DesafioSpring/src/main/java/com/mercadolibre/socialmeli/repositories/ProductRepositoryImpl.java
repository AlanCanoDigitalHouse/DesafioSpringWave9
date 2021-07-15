package com.mercadolibre.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.dtos.Product.response.PostUserDTO;
import com.mercadolibre.socialmeli.models.Post;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public void addPost(Post post) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Post> posts = loadDatabase();
        posts.add(post);
        try {
            objectMapper.writeValue(new File("src/main/resources/static/posts.json"), posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int createId() {
        int id = 0;
        List<Post> posts = loadDatabase();
        if (posts.size() == 0) {
            id = 1;
        } else {
            id = posts.get(posts.size() - 1).getId_post() + 1;
        }
        return id;
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = loadDatabase();
        return posts;
    }

    @Override
    public List<PostUserDTO> getLatestPost(Integer userId) {
        List<Post> posts = loadDatabase();
        List<PostUserDTO> latestsPostDTO = null;
        List<Post> latestsPost;
        try {
            if (Objects.nonNull(posts)) {
                List<Post> postsUser = posts.stream().filter(post -> post.getUserId().equals(userId)).collect(Collectors.toList());
                latestsPost = postsUser.stream().filter(post -> post.getDate().after(calculateDate())).collect(Collectors.toList());
                latestsPostDTO = latestsPost.stream().map(post -> new PostUserDTO(post)).collect(Collectors.toList());
            }
        } catch (NullPointerException e) {
            return null;
        }
        return latestsPostDTO;
    }

    private List<Post> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("src/main/resources/static/posts.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<Post> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Post>> typeReference = new TypeReference<>() {
        };
        List<Post> posts = new ArrayList<>();
        try {
            posts = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return posts;
    }

    private Date calculateDate() {
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, -15);
        Date start = cal.getTime();
        return start;
    }
}
