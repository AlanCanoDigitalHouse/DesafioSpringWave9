package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.PostDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserPostsResposeDTO;
import com.example.desafio1springboot.exceptions.PostNotValidDateException;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;
import com.example.desafio1springboot.repositories.IProductRepository;
import com.example.desafio1springboot.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    IProductRepository iProductRepository;
    IUserRepository iUserRepository;

    public ProductServiceImpl(IProductRepository iProductRepository, IUserRepository iUserRepository) {
        this.iProductRepository = iProductRepository;
        this.iUserRepository = iUserRepository;
    }

    @Override
    public void createNewPost(PostDTO post) throws UserSellerNotFoundExceptions, PostNotValidDateException {
        iUserRepository.getUserSellerById(post.getUserId());
        iProductRepository.addNewPost(post);
    }

    @Override
    public UserPostsResposeDTO listsPostsFromUser_(Integer userId) throws UserSellerNotFoundExceptions {
        iUserRepository.getUserSellerById(userId);
        return iProductRepository.listsPostsFromUser_(userId);
    }

    @Override
    public List<PostDTO> show() {
        return iProductRepository.show();
    }


}
