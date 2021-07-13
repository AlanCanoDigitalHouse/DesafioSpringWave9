package com.example.desafiospring.services.implementation;

import com.example.desafiospring.dtos.*;
import com.example.desafiospring.entities.Post;
import com.example.desafiospring.entities.Product;
import com.example.desafiospring.exceptions.DateInvalidException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.repositories.implementation.PostRepository;
import com.example.desafiospring.repositories.implementation.ProductRepository;
import com.example.desafiospring.services.IFollowerService;
import com.example.desafiospring.services.IPostService;
import com.example.desafiospring.services.IUserService;
import com.example.desafiospring.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private final IUserService userService;
    private final IFollowerService followerService;

    public PostService(PostRepository postRepository, ProductRepository productRepository, ObjectMapper objectMapper, IUserService userService, IFollowerService followerService) {
        this.postRepository = postRepository;
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.followerService = followerService;
    }

    @Override
    public PostDto createPost(PostCreateDto postCreateDto) throws DateInvalidException, UserNotExistException {
        this.userService.validateUserExist(postCreateDto.getUserId());
        Utils.validateDate(postCreateDto.getDate());
        Post post = this.postRepository.createPost(this.objectMapper.convertValue(postCreateDto, Post.class));
        PostDto postDto = this.objectMapper.convertValue(post, PostDto.class);
        Product product = this.objectMapper.convertValue(postCreateDto.getDetail(), Product.class);
        product.setId_post(postDto.getId_post());
        product = this.productRepository.createProduct(product);
        postDto.setDetail(this.objectMapper.convertValue(product, ProductDto.class));
        return postDto;
    }

    @Override
    public PostFollowedDto getRecentPosts(Long userId, String order) throws UserNotExistException {
        PostFollowedDto response = new PostFollowedDto();
        List<PostDto> posts = new ArrayList<>();
        UserFollowersDto userFollowers = this.followerService.getUserFollowed(userId, order);
        response.setUserId(userFollowers.getUserId());
        if (!userFollowers.getFollowed().isEmpty()) {
            List<Post> usersPost = this.postRepository.getPostsByUsersId(
                    userFollowers.getFollowed().stream().map(x ->
                            x.getUserId()).collect(Collectors.toList()));
            posts = this.objectMapper.convertValue(usersPost, new TypeReference<>() {});
            posts.forEach(x -> this.getProductByPostid(x));
            Comparator<PostDto> c = (o1, o2) ->
                    Utils.compareDates(o1.getDate(), o2.getDate(), order.equalsIgnoreCase("date_asc"));
            posts.sort(c);
        }
        response.setPosts(posts);
        return response;
    }

    private void getProductByPostid(PostDto post) {
        post.setDetail(this.objectMapper.convertValue(
                this.productRepository.getProductByIdPost(post.getId_post())
                ,ProductDto.class));
    }

}
