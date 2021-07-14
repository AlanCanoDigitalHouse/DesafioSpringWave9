package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.PostDTO;
import com.mercadolibre.socialmeli.exception.ServiceException;
import com.mercadolibre.socialmeli.repository.ProductRepository;
import com.mercadolibre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDTO createPost(PostDTO post) {
        if (userRepository.findUserByUserId(post.getUserId()).isEmpty())
            throw new ServiceException("User specified not found!");
        return productRepository.savePost(post);
    }
}
