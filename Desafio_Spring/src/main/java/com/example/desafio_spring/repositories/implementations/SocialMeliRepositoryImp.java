package com.example.desafio_spring.repositories.implementations;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.Product;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.repositories.interfaces.ISocialMeliRepository;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SocialMeliRepositoryImp implements ISocialMeliRepository {

    private static Map<Integer, User> database = new HashMap<>();
    private static Map<Integer, Post> posts = new HashMap<>();
    private static Map<Integer, Product> products = new HashMap<>();
    private static AtomicInteger counter = new AtomicInteger();
    private static AtomicInteger counterPost = new AtomicInteger();

    @Override
    public Map<Integer, User> getUsers() {
        return database;
    }

    @Override
    public Integer saveUser(UserRequestDTO userRequestDTO) {
        User user = new User(counter.getAndIncrement(),userRequestDTO.getUserName());
        database.put(user.getUserId(), user);
        return counter.get();
    }

    @Override
    public Integer saveUserByUser(User user) {
        database.put(user.getUserId(), user);
        return counter.get();
    }

    @Override
    public Integer getUserByUser(String userName) {
        Integer result = 0;
        for (Map.Entry<Integer, User> entry :
                database.entrySet()) {
            result = entry.getValue().getUserName().equals(userName) ? entry.getKey() : result;
        }
        return result;
    }

    @Override
    public User getUserById(Integer userId) {
        return database.get(userId);
    }

    @Override
    public Map<Integer, Post> getPosts() {
        return null;
    }

    @Override
    public Integer savePost(PostRequestDTO postRequestDTO) throws ParseException {
        Post post = new Post(postRequestDTO.getUserId(),counterPost.getAndIncrement(),postRequestDTO.getDate(),
                postRequestDTO.getDetail(),postRequestDTO.getCategory(),postRequestDTO.getPrice());
        post.getDetail().setProduct_id(counterPost.get());
        posts.put(post.getId_post(), post);
        return counterPost.get();
    }

    @Override
    public Integer getPostById(Integer postId) {
        Integer result = 0;
        for (Map.Entry<Integer, Post> entry :
                posts.entrySet()) {
            result = entry.getValue().getId_post().equals(postId) ? entry.getKey() : result;
        }
        return result;
    }
}
