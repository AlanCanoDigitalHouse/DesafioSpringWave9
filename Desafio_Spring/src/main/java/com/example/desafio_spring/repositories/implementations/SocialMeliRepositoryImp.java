package com.example.desafio_spring.repositories.implementations;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.ProductRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.dtos.response.UserResponseDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.repositories.interfaces.ISocialMeliRepository;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SocialMeliRepositoryImp implements ISocialMeliRepository {

    private static Map<Integer, User> database = new HashMap<>();
    private static Map<Integer, Post> posts = new HashMap<>();
    private static ArrayList<Post> postList = new ArrayList<>();
    private static AtomicInteger counter = new AtomicInteger();
    private static AtomicInteger counterPost = new AtomicInteger();
    private static AtomicInteger counterProduct = new AtomicInteger();

    /**
     * Metodo de repositorio para obtener todos los usuarios.
     * @return
     */
    @Override
    public Map<Integer, User> getUsers() {
        return database;
    }

    /**
     * Metodo para guardar un usuario con el dto user
     * @param userRequestDTO
     * @return
     */
    @Override
    public Integer saveUser(UserRequestDTO userRequestDTO) {
        User user = new User(counter.getAndIncrement(),userRequestDTO.getUserName());
        database.put(user.getUserId(), user);
        return counter.get();
    }

    /**
     * Metodo para guardar un usuario con la entidad user
     * @param user
     * @return
     */
    @Override
    public Integer saveUserByUser(User user) {
        database.put(user.getUserId(), user);
        return counter.get();
    }

    /**
     * Metodo para obtener un usuario a través de su username
     * @param userName
     * @return
     */
    @Override
    public Integer getUserByUser(String userName) {
        Integer result = 0;
        for (Map.Entry<Integer, User> entry :
                database.entrySet()) {
            result = entry.getValue().getUserName().equals(userName) ? entry.getKey() : result;
        }
        return result;
    }

    /**
     * Metodo para obtener el usuario por su Id
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Integer userId) {
        return database.get(userId);
    }
    /*------------------------- POSTS ---------------------------*/
    /**
     * Metodo para retornar todos los post
     * @return
     */
    @Override
    public Map<Integer, Post> getPosts() {
        return posts;
    }

    /**
     * Metodo para retornar una lista con todos los post
     * @return
     */
    @Override
    public ArrayList<Post> getPostList() {
        return postList;
    }

    /**
     * Metodo para guardar todos los post en un map
     * @param postRequestDTO
     * @return
     * @throws ParseException
     */
    @Override
    public Integer savePost(PostRequestDTO postRequestDTO) throws ParseException {
        ArrayList<ProductRequestDTO> products = new ArrayList<>();
        products.add(postRequestDTO.getDetail());
        Post post = new Post(postRequestDTO.getUserId(),counterPost.getAndIncrement(),postRequestDTO.getDate(),
                products,postRequestDTO.getCategory(),postRequestDTO.getPrice());
        post.getDetail().stream().forEach(x -> x.setProduct_id(counterProduct.incrementAndGet()));
        posts.put(post.getId_post(), post);
        return counterPost.get();
    }
    /**
     * Metodo para guardar los post en una lista
     * @param postRequestDTO
     * @return
     * @throws ParseException
     */
    public Integer saveListPost(PostRequestDTO postRequestDTO) throws ParseException {
        ArrayList<ProductRequestDTO> products = new ArrayList<>();
        products.add(postRequestDTO.getDetail());
        Post post = new Post(postRequestDTO.getUserId(),counterPost.getAndIncrement(),postRequestDTO.getDate(),
                products,postRequestDTO.getCategory(),postRequestDTO.getPrice());
        post.getDetail().stream().forEach(x -> x.setProduct_id(counterProduct.incrementAndGet()));
        postList.add(post);
        return counterPost.get();
    }

    /**
     * Metodo para obtener un post por su id ingresado
     * @param postId
     * @return
     */
    @Override
    public Integer getPostById(Integer postId) {
        Integer result = 0;
        for (Map.Entry<Integer, Post> entry :
                posts.entrySet()) {
            result = entry.getValue().getId_post().equals(postId) ? entry.getKey() : result;
        }
        return result;
    }

    /**
     * verificar si un usuario está siendo seguido por otro
     * @param userId
     * @param userIdToFollow
     * @return
     */
    @Override
    public boolean isFollowedBy(Integer userId, Integer userIdToFollow) {
        User follwer = database.get(userId);
        if (!Objects.nonNull(follwer)){
            return false;
        }
        Optional<UserResponseDTO> isFollower = follwer.getFollowed().stream().filter(x ->x.equals(userIdToFollow)).findFirst();
        return isFollower.isPresent();
    }

}
