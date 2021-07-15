package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.*;
import com.example.desafiospring.exceptions.UserDontHavePostsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class SocialMediaRepositoryImpl implements SocialMediaRepository{


    private final ObjectMapper MAPPER;
    private final String JSON_FILE_PATH = "src/main/resources/db/usersDb.json";
    private final String JSON_POST_FILE_PATH = "src/main/resources/db/postsDb.json";
    private AtomicInteger idPost = new AtomicInteger(0);
    private AtomicInteger idProduct = new AtomicInteger(0);

    public SocialMediaRepositoryImpl() {
        MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Override
    public Optional<User> findById(int userId) {
        JsonDatabaseUsersDto jsonDatabaseUsersDto = loadFile();
        return jsonDatabaseUsersDto.getUsers()
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public List<Post> findPostsByUserId(Integer userId) throws UserDontHavePostsException {
        JsonDatabasePostsDto jsonDatabasePostsDto = loadPostFile();
        if(jsonDatabasePostsDto.getUserPosts().isEmpty()) {
            throw new UserDontHavePostsException();
        }
        if(jsonDatabasePostsDto.getUserPosts().get(userId) == null) {
            throw new UserDontHavePostsException();
        }
        return new ArrayList<>(jsonDatabasePostsDto.getUserPosts().get(userId).values());
    }

    @Override
    public List<Post> findPostsByUserIds(List<Integer> ids)  {
        List<Post> posts = new ArrayList<>();
        JsonDatabasePostsDto jsonDatabasePostsDto = loadPostFile();
        List<Map<Integer,Post>> userPosts = jsonDatabasePostsDto.getUserPosts()
                .entrySet()
                .stream()
                .filter(entry -> ids.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        userPosts.forEach(postMap -> {
            posts.addAll(postMap.values());
        });

        return posts;
    }

    @Override
    public void saveFollowers(int userId, User user) {
        JsonDatabaseUsersDto jsonDatabaseUsersDto = loadFile();
        Optional<User> optionalUser = jsonDatabaseUsersDto.getUsers()
                .stream()
                .filter(userOptional -> userOptional.getUserId().equals(userId))
                .findFirst();

        optionalUser.get().addFollowers(user);
        saveFile(jsonDatabaseUsersDto);
    }

    @Override
    public void saveFollowed(int userId, User user) {
        JsonDatabaseUsersDto jsonDatabaseUsersDto = loadFile();
        Optional<User> optionalUser = jsonDatabaseUsersDto.getUsers()
                .stream()
                .filter(userOptional -> userOptional.getUserId().equals(userId))
                .findFirst();

        optionalUser.get().addFollowed(user);
        saveFile(jsonDatabaseUsersDto);
    }

    @Override
    public Post savePost(Post post) {
        post.setPostId(idPost.incrementAndGet());
        post.getDetail().setProduct_id(idProduct.incrementAndGet());
        JsonDatabasePostsDto jsonDatabasePostsDto = loadPostFile();
        jsonDatabasePostsDto.addPost(post);
        savePostFile(jsonDatabasePostsDto);
       return post;
    }

    @Override
    public void removeFollower(int userId, User user) {
        JsonDatabaseUsersDto jsonDatabaseUsersDto = loadFile();
        Optional<User> optionalUser = getUserById(userId, jsonDatabaseUsersDto);
        optionalUser.get().removeFollower(user);
        saveFile(jsonDatabaseUsersDto);
    }

    @Override
    public void removeFollowed(int userId, User user) {
        JsonDatabaseUsersDto jsonDatabaseUsersDto = loadFile();
        Optional<User> optionalUser = getUserById(userId, jsonDatabaseUsersDto);
        optionalUser.get().removeFollowed(user);
        saveFile(jsonDatabaseUsersDto);
    }

    private Optional<User> getUserById (int userId , JsonDatabaseUsersDto jsonDatabaseUsersDto) {
        return jsonDatabaseUsersDto.getUsers()
                .stream()
                .filter(userOptional -> userOptional.getUserId().equals(userId))
                .findFirst();
    }

    private void saveFile(JsonDatabaseUsersDto jsonDatabaseUsersDTO) {
        try {
            File file = new File(JSON_FILE_PATH);
            MAPPER.writeValue(file, jsonDatabaseUsersDTO);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }


    private JsonDatabaseUsersDto loadFile(){

        try{
            return MAPPER
                    .readValue(Paths.get(JSON_FILE_PATH).toFile(), JsonDatabaseUsersDto.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void savePostFile(JsonDatabasePostsDto jsonDatabasePostsDto) {
        try {
            File file = new File(JSON_POST_FILE_PATH);
            MAPPER.writeValue(file, jsonDatabasePostsDto);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }


    private JsonDatabasePostsDto loadPostFile(){

        try{
            return MAPPER
                    .readValue(Paths.get(JSON_POST_FILE_PATH).toFile(), JsonDatabasePostsDto.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
