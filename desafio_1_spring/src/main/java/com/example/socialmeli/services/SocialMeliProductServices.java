package com.example.socialmeli.services;

import com.example.socialmeli.dtos.requests.RequestPostDto;
import com.example.socialmeli.dtos.responses.*;
import com.example.socialmeli.exceptions.IncompatibleRequest;
import com.example.socialmeli.exceptions.InvalidOrder;
import com.example.socialmeli.exceptions.ProductNotFound;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.handlers.ProductHandler;
import com.example.socialmeli.handlers.UserHandler;
import com.example.socialmeli.models.Product;
import com.example.socialmeli.models.User;
import com.example.socialmeli.repositories.ProductRepository;
import com.example.socialmeli.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class SocialMeliProductServices implements ISocialMeliProductServices {
    private static UserRepository userRepository;
    private static ProductRepository productRepository;

    public SocialMeliProductServices(){
        userRepository = new UserRepository();
        productRepository = new ProductRepository();

    }

    public ResponseRequestDto post(RequestPostDto requestPostDto) throws UserNotFound, IncompatibleRequest {
        User user = userRepository.find(requestPostDto.getUserId());
        try{
            Product product = productRepository.find(requestPostDto.getDetail().getProductName());
            UserHandler.addUserPost(user,requestPostDto,product);
        }catch (ProductNotFound e) {
            Product product = ProductHandler.dtoToProducto(requestPostDto.getDetail());
            productRepository.add(product);
            UserHandler.addUserPost(user,requestPostDto,product);
        }

        return new ResponseRequestDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),"Posteo exitoso");
    }

    public ResponsePostsListDto getPostsInfo(Integer userId, String order) throws UserNotFound, InvalidOrder {
        User user = userRepository.find(userId);//MEJOR HAGO QUE EL .FIND TIRE EL ERROR, ASI NO LO HACEN TODOS LOS METODOS
        return UserHandler.getUserPostsList(user,order);
    }

}
