package com.example.desafiospring.utils;

import com.example.desafiospring.dtos.response.*;
import com.example.desafiospring.exceptions.TypeNotFoundException;
import com.example.desafiospring.utils.importsmodels.Post;
import com.example.desafiospring.utils.importsmodels.Product;
import com.example.desafiospring.utils.importsmodels.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Factory {
    public static UserResponseDto factoryUser(UserResponseDto user, String type) {
        if (type.equals("Client")) return new ClientResponseDto(user.getId(), user.getUserName());
        if (type.equals("Seller"))  return new SellerResponseDto(user.getId(), user.getUserName());
         throw new TypeNotFoundException("The type given in the argument does not exist");
    }

    public static List<PostResponseDto> generatePost(){
        List<PostResponseDto> listGenerated = new ArrayList<>();
        List<Post> listPost = getAllPost();
        List<Product> listProducts = getAllProducts();
        for (Post post:listPost) {
            var productId = listProducts.get(getRandomNumber());
            var product = new ProductResponseDto(
                    (int) productId.getProductId(),productId.getProductName(),
                    productId.getBrand(),productId.getColor(),productId.getNotes());
            var postDto = new PostResponseDto((int) post.getUserId(),
                    post.getDate(),(int) post.getCategory(),(double) post.getPrice(),product);
            listGenerated.add(postDto);
        }
        return listGenerated;
    }

    public static List<UserResponseDto> generateUsers() {
        List<UserResponseDto> listGenerated = new ArrayList<>();
        var listUser = getAllUsers();
        int iterator = 0;
        int sellers = 70;
        for (User user:listUser) {
            if (iterator <sellers){
               listGenerated.add(factoryUser(
                       new UserResponseDto((int) user.getUserID(), user.getUserName()),"Client"));
            }else{
                listGenerated.add(factoryUser(
                        new UserResponseDto((int) user.getUserID(),user.getUserName()),"Seller"));
            }
            iterator++;
        }
        return listGenerated;
    }

    private static Integer getRandomNumber(){
        Random rnd = new Random();
        return rnd.nextInt(100);
    }

    public static Integer generateId(){
        Random rnd = new Random();
        return 100000 + rnd.nextInt(900000);
    }

    private static List<Post> getAllPost() {
        File file = loadFile("post");
        if (Objects.nonNull(file)){
            List<Post> post =loadJsonPost(file);
            if (Objects.nonNull(post)){
                return post;
            }else{
                throw new Error("File doesn't match with the entity class");
            }
        }else{
            throw  new Error("Not file found");
        }
    }

    private static List<Product> getAllProducts(){
        File file = loadFile("products");
        if (Objects.nonNull(file)){
            List<Product> products = loadJsonProducts(file);
            if (Objects.nonNull(products)){
                return products;
            }else{
                throw new Error("File doesn't match with the entity class");
            }
        }else{
            throw  new Error("Not file found");
        }
    }

    private static  List<User> getAllUsers(){
        File file = loadFile("users");
        if (Objects.nonNull(file)){
            List<User> users = loadJsonUsers(file);
            if (Objects.nonNull(users)){
                return users;
            }else{
                throw new Error("File doesn't match with the entity class");
            }
        }else{
            throw  new Error("Not file found");
        }
    }

    private static List<User> loadJsonUsers(File file){
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<User>> typeRef = new TypeReference<>(){};
        List<User> users = null;
        try{
            users = obj.readValue(file,typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static List<Product> loadJsonProducts(File file){
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<Product>> typeRef = new TypeReference<>(){};
        List<Product> products = null;
        try{
            products = obj.readValue(file,typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
    private static List<Post> loadJsonPost(File file){
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<Post>> typeRef = new TypeReference<>(){};
        List<Post> posts = null;
        try{
            posts = obj.readValue(file,typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private static File loadFile(String filename){
        File file = null;
        try {
            file = ResourceUtils.getFile(String.format("classpath:static/%s.json",filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }
}

