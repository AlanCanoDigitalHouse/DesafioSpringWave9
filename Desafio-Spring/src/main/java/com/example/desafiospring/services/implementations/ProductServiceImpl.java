package com.example.desafiospring.services.implementations;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.requests.UserIDAndOrderRequestDTO;
import com.example.desafiospring.DTOS.responses.*;
import com.example.desafiospring.entities.PostEntity;
import com.example.desafiospring.repository.interfaces.ProductRepository;
import com.example.desafiospring.services.interfaces.FollowService;
import com.example.desafiospring.services.interfaces.PostService;
import com.example.desafiospring.services.interfaces.ProductService;
import com.example.desafiospring.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    UserService userService;
    PostService postService;
    FollowService followService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, PostService postService, FollowService followService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.postService = postService;
        this.followService = followService;
    }

    @Override
    public NewPostResponseDTO addNewPost(NewPostRequestDTO newPostRequestDTO) {
        userService.validateUsersExistence(newPostRequestDTO.getUserId());
        Integer productId = productRepository.addProduct(newPostRequestDTO.getDetail());
        Integer postId = postService.addPost(newPostRequestDTO, productId);
        return new NewPostResponseDTO(productId, postId);
    }

    @Override
    public FollowedProductListResponseDTO followedProductList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO) {
        Integer userId = userIDAndOrderRequestDTO.getUserId();
        userService.validateUsersExistence(userId);
        List<Integer> followedIds = followService.getFollowedIDs(userId);
        List<PostEntity> posts = postService.getRecentPostsOf(followedIds, userIDAndOrderRequestDTO.getOrder());
        return new FollowedProductListResponseDTO(userId, getPostResponseDTOS(posts));
    }

    private List<PostResponseDTO> getPostResponseDTOS(List<PostEntity> posts) {
        return posts.stream()
                .map(pe -> new PostResponseDTO(
                        pe.getPostId(),
                        pe.getDate(),
                        productRepository.getProductResponseDTO(pe.getProductId()),
                        pe.getCategory(),
                        pe.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public NewPostResponseDTO addNewPromoPost(NewPostRequestDTO newPostRequestDTO) {
        validatePromoPost(newPostRequestDTO);
        Integer productId = productRepository.addProduct(newPostRequestDTO.getDetail());
        Integer postId = postService.addPost(newPostRequestDTO, productId);
        return new NewPostResponseDTO(productId, postId);
    }

    @Override
    public PromoProductCountResponseDTO countPromoProducts(OnlyUserIDRequestDTO onlyUserIDRequestDTO) {
        Integer userId = onlyUserIDRequestDTO.getUserId();
        userService.validateUsersExistence(userId);
        Set<Integer> productIds = postService.getPromoProductIDs(userId);
        return new PromoProductCountResponseDTO(
                userId,
                userService.getUserByID(userId).getUserName(),
                productRepository.getProductResponseDTOSByID(productIds).size());
    }

    @Override
    public PromoProductListResponseDTO promoProductList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO) {
        Integer userId = userIDAndOrderRequestDTO.getUserId();
        userService.validateUsersExistence(userId);
        List<PostEntity> promoPosts = postService.getPromoPostsOf(userId);
        return new PromoProductListResponseDTO(
                userId,
                userService.getUserByID(userId).getUserName(),
                getPromoPostResponseDTOS(promoPosts,userIDAndOrderRequestDTO.getOrder()));
    }

    private Comparator<PostEntity> getProductNameComparator(String order) {
        Comparator<PostEntity> c = Comparator.comparing(p -> productRepository.getProductNameByID(p.getProductId()));
        return "name_desc".equals(order) ? c.reversed() : c;
    }

    private List<PromoPostResponseDTO> getPromoPostResponseDTOS(List<PostEntity> posts, String order) {
        return posts.stream()
                .sorted(getProductNameComparator(order))
                .map(pe -> new PromoPostResponseDTO(
                        pe.getPostId(),
                        pe.getDate(),
                        productRepository.getProductResponseDTO(pe.getProductId()),
                        pe.getCategory(),
                        pe.getPrice(),
                        pe.getHasPromo(),
                        pe.getDiscount()))
                .collect(Collectors.toList());
    }

    private void validatePromoPost(NewPostRequestDTO newPostRequestDTO) {
        userService.validateUsersExistence(newPostRequestDTO.getUserId());

        if (!Boolean.TRUE.equals(newPostRequestDTO.getHasPromo())) {
            throw new IllegalArgumentException("Promo Posts must have the 'hasPromo' value set to true");
        }
        if (newPostRequestDTO.getDiscount() == null || newPostRequestDTO.getDiscount() < 0 || newPostRequestDTO.getDiscount() > 1) {
            throw new IllegalArgumentException("Promo Posts must have the 'discount' value set between 0 and 1");
        }
    }
}
