package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.*;
import com.example.desafio1springboot.exceptions.*;
import com.example.desafio1springboot.handlers.ServiceHandler;
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
        iUserRepository.getUserSellerByIdAndCheckIfExists(post.getUserId());
        iProductRepository.addNewPost(post);
    }

    @Override
    public UserPostsResposeDTO listsPostsFromUser_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        ServiceHandler.checkIfOrder_Validation(order, "date_asc", "date_desc");
        iUserRepository.getUserSellerByIdAndCheckIfExists(userId);
        UserPostsResposeDTO<PostResponseDTO> userPostsResposeDTO = iProductRepository.listsPostsFromUser_(userId);

        if(order.equals("date_desc"))
            userPostsResposeDTO.getPosts().sort((d1, d2) -> d1.getDate().compareTo(d2.getDate()));

        return userPostsResposeDTO;
    }

    @Override
    public UserPostsResposeDTO getPostsInPromoByUser_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        ServiceHandler.checkIfOrder_Validation(order, "nameProduct_asc", "nameProduct_desc");

        var user = iUserRepository.getUserSellerByIdAndCheckIfExists(userId);
        var posts =iProductRepository.getPromoPostByUser_(userId);
        List<PromoPostResponseDTO> promoPostResponseDTOS = posts.stream().map(PromoPostResponseDTO::toPromoPostDTO).collect(Collectors.toList());

        UserPostsResposeDTO<PromoPostResponseDTO> userPostsResposeDTO = new UserPostsResposeDTO(userId, user.getUserName(), promoPostResponseDTOS);

        userPostsResposeDTO.getPosts().sort((a, b) -> a.getDetail().getProductName().compareTo(b.getDetail().getProductName()));

        if(order.equals("nameProduct_desc"))
            userPostsResposeDTO.getPosts().sort((a, b) -> b.getDetail().getProductName().compareTo(a.getDetail().getProductName()));

        return userPostsResposeDTO;
    }

    @Override
    public void createNewPromoPost(PostInPromoDTO post) throws PostNotValidDateException {
        iProductRepository.addNewPromoPost(post);
    }

    @Override
    public UserSellerResponseDTO countProductsInPromo(Integer userId) throws UserSellerNotFoundExceptions {
        return iProductRepository.postPromoMeBy_(userId, iUserRepository.getUserSellerByIdAndCheckIfExists(userId).getUserName());
    }
}
