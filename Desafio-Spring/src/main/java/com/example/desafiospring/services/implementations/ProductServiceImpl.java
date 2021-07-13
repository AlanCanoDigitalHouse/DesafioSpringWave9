package com.example.desafiospring.services.implementations;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.responses.NewPostResponseDTO;
import com.example.desafiospring.repository.interfaces.PostRepository;
import com.example.desafiospring.repository.interfaces.ProductRepository;
import com.example.desafiospring.repository.interfaces.UserRepository;
import com.example.desafiospring.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    UserRepository userRepository;
    ProductRepository productRepository;
    PostRepository postRepository;

    public ProductServiceImpl(UserRepository userRepository, ProductRepository productRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.postRepository = postRepository;
    }

    @Override
    public NewPostResponseDTO addNewPost(NewPostRequestDTO newPostRequestDTO) {
        userRepository.validateExistOrException(newPostRequestDTO.getUserId());
        Integer productId = productRepository.addProduct(newPostRequestDTO.getDetail());
        Integer postId = postRepository.addPost(newPostRequestDTO,productId);
        return new NewPostResponseDTO(productId,postId);
    }
}
