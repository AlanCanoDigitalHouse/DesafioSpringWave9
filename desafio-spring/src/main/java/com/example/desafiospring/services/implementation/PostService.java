package com.example.desafiospring.services.implementation;

import com.example.desafiospring.dtos.*;
import com.example.desafiospring.entities.Post;
import com.example.desafiospring.enums.ConstantEnum;
import com.example.desafiospring.enums.ErrorMessageEnum;
import com.example.desafiospring.exceptions.DateInvalidException;
import com.example.desafiospring.exceptions.DiscountInvalidException;
import com.example.desafiospring.exceptions.PromoPostInvalidException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.repositories.implementation.PostRepository;
import com.example.desafiospring.services.IFollowerService;
import com.example.desafiospring.services.IPostService;
import com.example.desafiospring.services.IProductService;
import com.example.desafiospring.services.IUserService;
import com.example.desafiospring.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final IProductService productService;
    private final ObjectMapper objectMapper;
    private final IUserService userService;
    private final IFollowerService followerService;

    public PostService(PostRepository postRepository, IProductService productService, ObjectMapper objectMapper,
                       IUserService userService, IFollowerService followerService) {
        this.postRepository = postRepository;
        this.productService = productService;
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.followerService = followerService;
    }

    @Override
    public PostDto createPost(PostCreateDto postToCreate) throws DateInvalidException,
            UserNotExistException, IOException {
        return this.createBasicPost(
                this.objectMapper.convertValue(postToCreate, Post.class),
                this.objectMapper.convertValue(postToCreate.getDetail(), ProductDto.class),
                postToCreate.getUserId(), postToCreate.getDate());
    }

    @Override
    public PostDto createPromoPost(PostPromoCreateDto postToCreate) throws DateInvalidException,
            UserNotExistException, PromoPostInvalidException, IOException, DiscountInvalidException {
        if (postToCreate.getHasPromo()) {
            if (Objects.isNull(postToCreate.getDiscount()))
                throw new PromoPostInvalidException(ErrorMessageEnum.PROMO_POST_INVALID_EXCEPTION);
            if (postToCreate.getDiscount() < 0 )
                throw new DiscountInvalidException(ErrorMessageEnum.DISCOUNT_INVALID_EXCEPTION_NEGATIVE);
            if (postToCreate.getDiscount() > 1 )
                throw new DiscountInvalidException(ErrorMessageEnum.DISCOUNT_INVALID_EXCEPTION_MAX);
        }
        return this.createBasicPost(
                this.objectMapper.convertValue(postToCreate, Post.class),
                this.objectMapper.convertValue(postToCreate.getDetail(), ProductDto.class),
                postToCreate.getUserId(), postToCreate.getDate());
    }

    @Override
    public PostFollowedDto getRecentPosts(Long userId, String order) throws UserNotExistException, IOException {
        PostFollowedDto response = new PostFollowedDto();
        List<PostDto> posts = new ArrayList<>();
        UserFollowersDto userFollowers = this.followerService.getUserFollowed(userId, order);
        response.setUserId(userFollowers.getUserId());
        if (!userFollowers.getFollowed().isEmpty()) {
            List<Post> usersPost = this.postRepository.getPostsByUsersId(
                    userFollowers.getFollowed().stream().map(UserBasicDto::getUserId).collect(Collectors.toList()));
            posts = this.objectMapper.convertValue(usersPost, new TypeReference<>() {});
            for (PostDto post: posts)
                this.getProductByPost(post);
            Comparator<PostDto> c = (o1, o2) ->
                    Utils.compareDates(o1.getDate(), o2.getDate(), order.equalsIgnoreCase(ConstantEnum.ORDER_DATE_DESC));
            posts.sort(c);
        }
        response.setPosts(posts);
        return response;
    }

    @Override
    public UserPostDto countPostsByUserId(Long userId) throws UserNotExistException, IOException {
        UserDto userDto = this.userService.findByUserIdAndType(userId, true);
        UserPostDto respond = new UserPostDto();
        respond.setUserId(userDto.getUserId());
        respond.setUserName(userDto.getUserName());
        respond.setPromoproducts_count(this.postRepository.countPostsByUserId(userId, true));
        return respond;
    }

    @Override
    public UserPostDto getPostsByUserId(Long userId, String order) throws UserNotExistException, IOException {
        UserDto userDto = this.userService.findByUserIdAndType(userId, true);
        UserPostDto respond = new UserPostDto();
        respond.setUserId(userDto.getUserId());
        respond.setUserName(userDto.getUserName());
        List<PostDto> posts = this.objectMapper.convertValue(
                this.postRepository.getPostsByUserId(userId, true), new TypeReference<>() {});
        for (PostDto post: posts)
            this.getProductByPost(post);
        respond.setPosts(posts);
        Comparator<PostDto> c = (o1, o2) ->
                Utils.compareDates(o1.getDate(), o2.getDate(), order.equalsIgnoreCase(ConstantEnum.ORDER_DATE_DESC));
        respond.getPosts().sort(c);
        return respond;
    }

    private PostDto createBasicPost(Post postToCreate, ProductDto productToCreate, Long userId, String date)
            throws UserNotExistException, DateInvalidException, IOException {
        this.userService.findByUserId(userId);
        Utils.validateDate(date);
        Post post = this.postRepository.createPost(postToCreate);
        PostDto postDto = this.objectMapper.convertValue(post, PostDto.class);
        productToCreate = this.productService.createProduct(productToCreate, postDto.getId_post());
        postDto.setDetail(this.objectMapper.convertValue(productToCreate, ProductDto.class));
        return postDto;
    }

    private void getProductByPost(PostDto post) throws IOException {
        post.setDetail(this.objectMapper.convertValue(
                this.productService.getProductByIdPost(post)
                ,ProductDto.class));
    }

}
