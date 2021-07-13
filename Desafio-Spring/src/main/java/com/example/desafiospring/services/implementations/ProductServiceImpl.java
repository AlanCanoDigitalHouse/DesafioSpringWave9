package com.example.desafiospring.services.implementations;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowedProductListResponseDTO;
import com.example.desafiospring.DTOS.responses.NewPostResponseDTO;
import com.example.desafiospring.repository.interfaces.FollowRepository;
import com.example.desafiospring.repository.interfaces.PostRepository;
import com.example.desafiospring.repository.interfaces.ProductRepository;
import com.example.desafiospring.repository.interfaces.UserRepository;
import com.example.desafiospring.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Integer postId = postRepository.addPost(newPostRequestDTO,productId);
        return new NewPostResponseDTO(productId,postId);
    }

    @Override
    public FollowedProductListResponseDTO followedProductList(OnlyUserIDRequestDTO onlyUserIDRequestDTO) {
        Integer userId = onlyUserIDRequestDTO.getUserId();
        userRepository.validateExistOrException(userId);
        List<Integer> followedIds = followRepository.getFollowedIDs(userId);
        return new FollowedProductListResponseDTO(userId,postRepository.getRecentPostsOf(followedIds));
    }
}
