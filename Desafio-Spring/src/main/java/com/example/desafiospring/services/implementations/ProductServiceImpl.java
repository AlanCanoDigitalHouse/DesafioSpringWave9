package com.example.desafiospring.services.implementations;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.requests.UserIDAndOrderRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowedProductListResponseDTO;
import com.example.desafiospring.DTOS.responses.NewPostResponseDTO;
import com.example.desafiospring.DTOS.responses.PromoProductCountResponseDTO;
import com.example.desafiospring.DTOS.responses.PromoProductListResponseDTO;
import com.example.desafiospring.repository.interfaces.FollowRepository;
import com.example.desafiospring.repository.interfaces.PostRepository;
import com.example.desafiospring.repository.interfaces.ProductRepository;
import com.example.desafiospring.repository.interfaces.UserRepository;
import com.example.desafiospring.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    UserRepository userRepository;
    ProductRepository productRepository;
    PostRepository postRepository;
    FollowRepository followRepository;

    public ProductServiceImpl(UserRepository userRepository, ProductRepository productRepository, PostRepository postRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.postRepository = postRepository;
        this.followRepository = followRepository;
    }

    @Override
    public NewPostResponseDTO addNewPost(NewPostRequestDTO newPostRequestDTO) {
        userRepository.validateExistOrException(newPostRequestDTO.getUserId());
        Integer productId = productRepository.addProduct(newPostRequestDTO.getDetail());
        Integer postId = postRepository.addPost(newPostRequestDTO, productId);
        return new NewPostResponseDTO(productId, postId);
    }

    @Override
    public FollowedProductListResponseDTO followedProductList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO) {
        Integer userId = userIDAndOrderRequestDTO.getUserId();
        userRepository.validateExistOrException(userId);
        List<Integer> followedIds = followRepository.getFollowedIDs(userId);
        return new FollowedProductListResponseDTO(userId, postRepository.getRecentPostsOf(followedIds, userIDAndOrderRequestDTO.getOrder()));
    }

    @Override
    public NewPostResponseDTO addNewPromoPost(NewPostRequestDTO newPostRequestDTO) {
        validatePromoPost(newPostRequestDTO);
        Integer productId = productRepository.addProduct(newPostRequestDTO.getDetail());
        Integer postId = postRepository.addPost(newPostRequestDTO, productId);
        return new NewPostResponseDTO(productId, postId);
    }

    @Override
    public PromoProductCountResponseDTO countPromoProducts(OnlyUserIDRequestDTO onlyUserIDRequestDTO) {
        Integer userId = onlyUserIDRequestDTO.getUserId();
        userRepository.validateExistOrException(userId);
        Set<Integer> productIds = postRepository.getPromoProductIDs(userId);
        return new PromoProductCountResponseDTO(
                userId,
                userRepository.getUserByID(userId).getUserName(),
                productRepository.getProductResponseDTOSByID(productIds).size());
    }

    @Override
    public PromoProductListResponseDTO promoProductList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO) {
        Integer userId = userIDAndOrderRequestDTO.getUserId();
        userRepository.validateExistOrException(userId);
        return new PromoProductListResponseDTO(userId,userRepository.getUserByID(userId).getUserName(), postRepository.getPromoPostsOf(userId, userIDAndOrderRequestDTO.getOrder()));
    }

    private void validatePromoPost(NewPostRequestDTO newPostRequestDTO) {
        userRepository.validateExistOrException(newPostRequestDTO.getUserId());

        if (!Boolean.TRUE.equals(newPostRequestDTO.getHasPromo())) {
            throw new IllegalArgumentException("Promo Posts must have the 'hasPromo' value set to true");
        }
        if (newPostRequestDTO.getDiscount() == null || newPostRequestDTO.getDiscount() < 0 || newPostRequestDTO.getDiscount() > 1) {
            throw new IllegalArgumentException("Promo Posts must have the 'discount' value set between 0 and 1");
        }
    }
}
