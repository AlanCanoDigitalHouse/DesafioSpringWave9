package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.PostDTO;
import com.mercadolibre.socialmeli.dto.UserDTO;
import com.mercadolibre.socialmeli.dto.response.PostResponseListDTO;
import com.mercadolibre.socialmeli.exception.ServiceException;
import com.mercadolibre.socialmeli.repository.ProductRepository;
import com.mercadolibre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    UserRepository userRepository;
    UserService userService;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository,
                              UserService userService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public PostDTO createPost(PostDTO post) {
        if (userRepository.findUserByUserId(post.getUserId()).isEmpty())
            throw new ServiceException("User specified not found!");
        return productRepository.savePost(post);
    }

    @Override
    public PostResponseListDTO findPostFromFollowedUsers(Integer followerId) {
        List<UserDTO> followed = userService.getFollowed(followerId).getFollowed();
        final List<PostDTO> allPosts = new ArrayList<>();
        if (!followed.isEmpty()) {
            followed.forEach(
                    followedUser ->
                            allPosts.addAll(productRepository.findPostByUserId(followedUser.getUserID()))
            );
        }
        final PostResponseListDTO postResponseList = new PostResponseListDTO();
        postResponseList.setUserId(followerId);
        postResponseList.setPosts(allPosts);
        return postResponseList;
    }

    @Override
    public PostResponseListDTO findPostFromFollowedUsers(Integer followerId, String order) {
        PostResponseListDTO response = findPostFromFollowedUsers(followerId);
        if (order.equals("date_asc"))
            response.getPosts().sort(Comparator.comparing(PostDTO::getDate));
        else if (order.equals("date_desc"))
            response.getPosts().sort((post1, postDTO2) -> postDTO2.getDate().compareTo(post1.getDate()));
        return response;
    }
}
