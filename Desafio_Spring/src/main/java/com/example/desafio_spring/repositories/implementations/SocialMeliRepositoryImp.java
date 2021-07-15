package com.example.desafio_spring.repositories.implementations;

import com.example.desafio_spring.dtos.request.PostPromoRequestDTO;
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

    private static final Map<Integer, User> database = new HashMap<>();
    private static final Map<Integer, Post> posts = new HashMap<>();
    private static final ArrayList<Post> postList = new ArrayList<>();
    private static final AtomicInteger counter = new AtomicInteger();
    private static final AtomicInteger counterPost = new AtomicInteger();
    private static final AtomicInteger counterProduct = new AtomicInteger();

    /**
     * Metodo de repositorio para obtener todos los usuarios.
     * @return database
     */
    @Override
    public Map<Integer, User> getUsers() {
        return database;
    }

    /**
     * Metodo para guardar un usuario con el dto user
     * @param userRequestDTO datos requeridos del usuario
     * @return retorna el id del usuario
     */
    @Override
    public Integer saveUser(UserRequestDTO userRequestDTO) {
        User user = new User(counter.getAndIncrement(),userRequestDTO.getUserName());
        database.put(user.getUserId(), user);
        return counter.get();
    }

    /**
     * Metodo para guardar un usuario con la entidad user
     * @param user objeto de tipo user
     * @return retorna el id del usuario
     */
    @Override
    public Integer saveUserByUser(User user) {
        database.put(user.getUserId(), user);
        return counter.get();
    }

    /**
     * Metodo para obtener un usuario a través de su username
     * @param userName nombre del ususario
     * @return retorna la llave del diccionario
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
     * @param userId Entero, id del ususario
     * @return retorna un usuario por id
     */
    @Override
    public User getUserById(Integer userId) {
        return database.get(userId);
    }
    /*------------------------- POSTS ---------------------------*/
    /**
     * Metodo para retornar todos los post
     * @return  retorna todos los post
     */
    @Override
    public Map<Integer, Post> getPosts() {
        return posts;
    }

    /**
     * Metodo para retornar una lista con todos los post
     * @return retorna una lista de posts
     */
    @Override
    public ArrayList<Post> getPostList() {
        return postList;
    }

    /**
     * Metodo para guardar todos los post en un map
     * @param postRequestDTO recibe los datos del post
     * @return el id del post
     * @throws ParseException Excepción dado el caso no logre parsear la fecha
     */
    @Override
    public Integer savePost(PostRequestDTO postRequestDTO) throws ParseException {
        ArrayList<ProductRequestDTO> products = new ArrayList<>();
        products.add(postRequestDTO.getDetail());
        Post post = new Post(postRequestDTO.getUserId(),counterPost.getAndIncrement(),postRequestDTO.getDate(),
                products,postRequestDTO.getCategory(),postRequestDTO.getPrice());
        post.getDetail().forEach(x -> x.setProduct_id(counterProduct.getAndIncrement()));
        posts.put(post.getId_post(), post);
        return counterPost.get();
    }

    @Override
    public Integer savePostPromo(PostPromoRequestDTO postPromoRequestDTO) {
        ArrayList<ProductRequestDTO> products = new ArrayList<>();
        products.add(postPromoRequestDTO.getDetail());
        Post post = new Post(postPromoRequestDTO.getUserId(),counterPost.getAndIncrement(),postPromoRequestDTO.getDate(),
                products,postPromoRequestDTO.getCategory(),postPromoRequestDTO.getPrice(), postPromoRequestDTO.isHasPromo(),
                postPromoRequestDTO.getDiscount());
        post.getDetail().forEach(x -> x.setProduct_id(counterProduct.getAndIncrement()));
        posts.put(post.getId_post(), post);
        return counterPost.get();
    }

    /**
     * Metodo para guardar los post en una lista
     * @param postRequestDTO Recibe los datos del post para guardarlos
     * @return el id del post guardado en la lista
     * @throws ParseException excepcion por si no puede parsear la fecha
     */
    public Integer saveListPost(PostRequestDTO postRequestDTO) throws ParseException {
        ArrayList<ProductRequestDTO> products = new ArrayList<>();
        products.add(postRequestDTO.getDetail());
        Post post = new Post(postRequestDTO.getUserId(),counterPost.getAndIncrement(),postRequestDTO.getDate(),
                products,postRequestDTO.getCategory(),postRequestDTO.getPrice());
        post.getDetail().forEach(x -> x.setProduct_id(counterProduct.getAndIncrement()));
        postList.add(post);
        return counterPost.get();
    }

    @Override
    public void saveListPostPromo(PostPromoRequestDTO postPromoRequestDTO) {
        ArrayList<ProductRequestDTO> products = new ArrayList<>();
        products.add(postPromoRequestDTO.getDetail());
        Post post = new Post(postPromoRequestDTO.getUserId(),counterPost.getAndIncrement(),postPromoRequestDTO.getDate(),
                products,postPromoRequestDTO.getCategory(),postPromoRequestDTO.getPrice(), postPromoRequestDTO.isHasPromo(),
                postPromoRequestDTO.getDiscount());
        post.getDetail().forEach(x -> x.setProduct_id(counterProduct.getAndIncrement()));
        postList.add(post);
    }

    /**
     * Metodo para obtener un post por su id ingresado
     * @param postId recibe el id del post
     * @return la llave del post en el map
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
     * @param userId recibe el id del ususario
     * @param userIdToFollow recibe el id del ususario a seguir
     * @return un true o false en caso de que ya lo siga o no lo siga respectivamente
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
