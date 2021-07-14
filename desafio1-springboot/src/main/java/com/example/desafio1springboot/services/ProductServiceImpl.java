package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.*;
import com.example.desafio1springboot.exceptions.*;
import com.example.desafio1springboot.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public UserPostsResposeDTO listsPostsFromUser_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        if(!order.equals("date_asc") && !order.equals("date_desc"))
            throw new OrderNotValidException();
        iUserRepository.getUserSellerById(userId);
        UserPostsResposeDTO<PostResponseDTO> userPostsResposeDTO = iProductRepository.listsPostsFromUser_(userId);

        if(order.equals("date_desc"))
            userPostsResposeDTO.getPosts().sort((d1, d2) -> d1.getDate().compareTo(d2.getDate()));

        return userPostsResposeDTO;
    }

    @Override
    public List<PostDTO> show() {
        return iProductRepository.show();
    }

    @Override
    public UserPostsResposeDTO getPostsInPromoByUser_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        var user = iUserRepository.getUserSellerById(userId);
        var posts =iProductRepository.getPromoPostByUser_(userId);
        List<PromoPostResponseDTO> promoPostResponseDTOS = posts.stream().map(PromoPostResponseDTO::toPromoPostDTO).collect(Collectors.toList());
        UserPostsResposeDTO<PromoPostResponseDTO> userPostsResposeDTO = new UserPostsResposeDTO(userId, user.getUserName(), promoPostResponseDTOS);
        if(!order.equals("nameProduct_asc") && !order.equals("nameProduct_desc"))
            throw new OrderNotValidException();
        userPostsResposeDTO.getPosts().sort((a, b) -> a.getDetail().getProductName().compareTo(b.getDetail().getProductName()));
        if(order.equals("nameProduct_desc"))
            userPostsResposeDTO.getPosts().sort((a, b) -> b.getDetail().getProductName().compareTo(a.getDetail().getProductName()));

        return userPostsResposeDTO;
    }

    @Override
    public void addNewPromoPost(PostInPromoDTO post) throws PostNotValidDateException {
        iProductRepository.addNewPromoPost(post);
    }

    @Override
    public UserSellerResponseDTO countProductsInPromo(Integer userId) throws UserSellerNotFoundExceptions {
        return iProductRepository.postPromoMeBy_(userId, iUserRepository.getUserSellerById(userId).getUserName());
    }
}
