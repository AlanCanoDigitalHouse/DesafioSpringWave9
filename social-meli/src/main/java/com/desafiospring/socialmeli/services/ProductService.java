package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.models.Post;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.handlers.ValidationHandler;
import com.desafiospring.socialmeli.repositories.BuyerRepository;
import com.desafiospring.socialmeli.repositories.ProductRepository;
import com.desafiospring.socialmeli.repositories.SellerRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService implements IProduct{

    BuyerRepository buyerRepository;
    SellerRepository sellerRepository;
    ProductRepository productRepository;

    public ProductService(BuyerRepository buyerRepository, SellerRepository sellerRepository, ProductRepository productRepository){
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Post addPost(Post post) throws UserException {
        ValidationHandler.validateSeller(post.getUserId(), sellerRepository);
        ValidationHandler.validateDate(post.getDate());
        productRepository.add(post);
        return post;
    }
}
