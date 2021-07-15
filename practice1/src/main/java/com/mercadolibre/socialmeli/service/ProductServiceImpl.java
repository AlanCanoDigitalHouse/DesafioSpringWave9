package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.UserDTO;
import com.mercadolibre.socialmeli.dto.request.PostDTO;
import com.mercadolibre.socialmeli.dto.request.PromoPostRequestDTO;
import com.mercadolibre.socialmeli.dto.response.PostPromoCountResponseDTO;
import com.mercadolibre.socialmeli.dto.response.PostPromoListResponse;
import com.mercadolibre.socialmeli.dto.response.PostPromoResponseDTO;
import com.mercadolibre.socialmeli.dto.response.PostResponseListDTO;
import com.mercadolibre.socialmeli.model.Post;
import com.mercadolibre.socialmeli.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    UserService userService;

    public ProductServiceImpl(ProductRepository productRepository,
                              UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public PostDTO createPost(PostDTO post) {
        UserDTO user = userService.findUserById(post.getUserId());
        final Post completePost = new Post();
        completePost.setId_post(post.getId_post());
        completePost.setUserId(post.getUserId());
        completePost.setDate(post.getDate());
        completePost.setDetail(post.getDetail());
        completePost.setCategory(post.getCategory());
        completePost.setPrice(post.getPrice());
        completePost.setHasPromo(Boolean.FALSE);
        completePost.setDiscount(0D);
        productRepository.savePost(completePost);
        return post;
    }

    @Override
    public void createPromoPost(PromoPostRequestDTO post) {
        UserDTO user = userService.findUserById(post.getUserId());
        final Post completePost = new Post();
        completePost.setId_post(post.getId_post());
        completePost.setUserId(user.getUserID());
        completePost.setDate(post.getDate());
        completePost.setDetail(post.getDetail());
        completePost.setCategory(post.getCategory());
        completePost.setPrice(post.getPrice());
        completePost.setHasPromo(post.getHasPromo());
        completePost.setDiscount(post.getDiscount());
        productRepository.savePost(completePost);
    }

    @Override
    public PostResponseListDTO findPostFromFollowedUsers(Integer followerId) {
        List<UserDTO> followed = userService.getFollowed(followerId).getFollowed();
        final List<PostDTO> allPosts = new ArrayList<>();
        if (!followed.isEmpty()) {
            followed.forEach(
                    followedUser ->
                            allPosts.addAll(productRepository.findPostByUserId(followedUser.getUserID())
                                    .stream().map(post -> new PostDTO(
                                            post.getUserId(), post.getId_post(), post.getDate(),
                                            post.getDetail(), post.getCategory(), post.getPrice()
                                    )).collect(Collectors.toCollection(ArrayList::new)))
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

    @Override
    public PostPromoCountResponseDTO countPromoPostsByUser(Integer userId) {
        final PostPromoCountResponseDTO response = new PostPromoCountResponseDTO();

        List<Post> posts = productRepository.findPostByUserId(userId)
                .stream()
                .filter(post ->
                        post.getHasPromo().equals(Boolean.TRUE))
                .collect(Collectors.toCollection(ArrayList::new));

        UserDTO user = userService.findUserById(userId);

        response.setUserName(user.getUserName());
        response.setUserId(user.getUserID());
        response.setPromoproducts_count(posts.size());
        return response;
    }

    @Override
    public PostPromoListResponse getPromoPostListByUser(Integer userId) {
        final PostPromoListResponse response = new PostPromoListResponse();
        List<PostPromoResponseDTO> posts = productRepository.findPostByUserId(userId)
                .stream()
                .filter(post ->
                        post.getHasPromo().equals(Boolean.TRUE))
                .map(post -> new PostPromoResponseDTO(post.getId_post(), post.getDate(),
                        post.getDetail(), post.getCategory(), post.getPrice(),
                        post.getHasPromo(), post.getDiscount()))
                .collect(Collectors.toCollection(ArrayList::new));

        UserDTO user = userService.findUserById(userId);

        response.setUserName(user.getUserName());
        response.setUserId(user.getUserID());
        response.setPosts(posts);
        return response;
    }
}
