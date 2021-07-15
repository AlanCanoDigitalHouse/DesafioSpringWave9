package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.Product.request.PostDTO;
import com.mercadolibre.socialmeli.dtos.Product.response.PostUserDTO;
import com.mercadolibre.socialmeli.dtos.Product.response.UserPostsDTO;
import com.mercadolibre.socialmeli.dtos.User.UserDTO;
import com.mercadolibre.socialmeli.dtos.UserResponseDTO;
import com.mercadolibre.socialmeli.exceptions.ExceptionOrder;
import com.mercadolibre.socialmeli.exceptions.ExceptionUserNotFound;
import com.mercadolibre.socialmeli.models.Post;
import com.mercadolibre.socialmeli.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    UserService userService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public UserResponseDTO createPost(PostDTO post) throws ExceptionUserNotFound {
        UserResponseDTO userResponseDTO = null;
        if (userService.findById(post.getUserId()) != null) {
            Integer newId = productRepository.createId();
            Post p = new Post(newId, post);
            productRepository.addPost(p);
            userResponseDTO = new UserResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Nuevo post creado", post);
        }
        return userResponseDTO;
    }

    @Override
    public List<Post> getAll() {
        return productRepository.getAllPost();
    }

    @Override
    public UserPostsDTO getAllPosts(Integer userId, String order) throws ExceptionUserNotFound, ExceptionOrder {
        UserPostsDTO userPostsDTO = null;
        List<UserDTO> users = userService.findById(userId).getFollowed();
        List<PostUserDTO> posts = new ArrayList<>();
        for (UserDTO user : users) {
            List<PostUserDTO> userPosts = productRepository.getLatestPost(user.getUserId());
            if (Objects.nonNull(userPosts))
                posts.addAll(userPosts);
        }
        orderList(order, posts);
        userPostsDTO = new UserPostsDTO(userId, posts);
        return userPostsDTO;
    }

    private void orderList(String order, List<PostUserDTO> posts) throws ExceptionOrder {
        if (order.equalsIgnoreCase("date_asc")) {
            //date ascendente (mas vieja a mas nueva)
            posts.sort((Comparator.comparing(post -> post.getDate())));
        } else if (order.equalsIgnoreCase("date_desc")) {
            Collections.sort(posts);
        } else throw new ExceptionOrder("Su parámetro de ordenamiento esta mal escrito. Puede ordenar por date_asc o date_desc");
    }
}
