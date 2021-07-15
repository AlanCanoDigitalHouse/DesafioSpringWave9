package com.example.demo.Repositories;

import com.example.demo.DTOs.FollowedPostsDTO;
import com.example.demo.Exceptions.*;
import com.example.demo.Handlers.GetDate;
import com.example.demo.Handlers.SortByDate;
import com.example.demo.Handlers.ValidateUser;
import com.example.demo.Models.ProductModel;
import com.example.demo.Models.UserModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import com.example.demo.Models.PostModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PostRepository implements IPostRepository {

    @Autowired
    IUserRepository userRepository;

    private List<ProductModel> products;
    private List<PostModel> posts;

    public PostRepository() {
        this.posts = loadDB();
        this.products = loadProductsDB();
    }


    //

    private List<PostModel> loadDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:posts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PostModel>> typeReference = new TypeReference<>() {};

        List<PostModel> dataBase = null;

        try {
            dataBase = objectMapper.readValue(file, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataBase;
    }

    private List<ProductModel> loadProductsDB() {
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:products.json");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ProductModel>> typeRef = new TypeReference<>() {};

        List<ProductModel> db = null;

        try {
            db = objectMapper.readValue(file, typeRef);
        } catch (Exception e){
            e.printStackTrace();
        }
        return db;
    }

    //

    @Override
    public void createPost(PostModel post) throws ExceptionHandler {
        // ValidateUser.validateUser(users, post.getUserId());
        List<PostModel> userPosts = getUserPostsById(post.getUserId());
        for(PostModel p:userPosts) {
            if(post == p) {
                throw new DuplicatedPostException();
            }
        }
        this.posts.add(post);
    }

    @Override
    public void createProduct(ProductModel product) {
        this.products.add(product);
    }

    //

    @Override
    public ProductModel getProductById(int productId) throws ExceptionHandler {
        for(ProductModel p:products){
            if (p.getProductId() == productId){
                return p;
            }
        }
        throw new InvalidProductException();
    }

    @Override
    public FollowedPostsDTO getFollowedPosts(int userId, String order, int daysBefore) throws ExceptionHandler {
        // ValidateUser.validateUser(users, userId);
        List<UserModel> following = userRepository.getListFollowing(userId);
        if(following.size() == 0) throw new NoFollowingException();
        List<PostModel> posts = new ArrayList<>();
        Date limitDate = GetDate.GetDate(new Date(), daysBefore);
        for(UserModel u:following) {
            List<PostModel> userPosts = getUserPostsById(u.getUserId());
            for(PostModel p:userPosts) {
                if (limitDate.compareTo(p.getDate()) <= 0) {
                    posts.add(p);
                }
            }
        }
        if(posts.size() == 0) {
            throw new NoPostsException();
        }
        SortByDate.sortByDate(posts, order);
        return new FollowedPostsDTO(userId, posts);
    }

    @Override
    public List<PostModel> getUserPostsById(int userId) throws ExceptionHandler {
        // ValidateUser.validateUser(users, userId);
        List<PostModel> posts = new ArrayList<>();
        for(PostModel p:this.posts) {
            if (p.getUserId() == userId){
                posts.add(p);
            }
        }
        if(posts.size() == 0) {
            throw new NoPostsException();
        }
        return posts;
    }

}
