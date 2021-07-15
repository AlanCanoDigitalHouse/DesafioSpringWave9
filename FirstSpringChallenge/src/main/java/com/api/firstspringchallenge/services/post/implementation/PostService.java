package com.api.firstspringchallenge.services.post.implementation;

import com.api.firstspringchallenge.dtos.request.PostRequestDTO;
import com.api.firstspringchallenge.dtos.request.PromoPostRequestDTO;
import com.api.firstspringchallenge.dtos.response.*;
import com.api.firstspringchallenge.manager.Manager;
import com.api.firstspringchallenge.models.Post;
import com.api.firstspringchallenge.models.Seller;
import com.api.firstspringchallenge.services.post.PostServiceI;
import com.api.firstspringchallenge.services.relation.implementation.RelationService;
import com.api.firstspringchallenge.services.user.implementation.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService implements PostServiceI {

    private final UserService sellerService;

    private final RelationService relationService;

    @Override
    public ResponseEntity newPost(PostRequestDTO postDTO) {
        Seller seller = (Seller) sellerService.findUserById(postDTO.getUserId());
        Post post = new Post(postDTO.getDate(), postDTO.getDetail(), postDTO.getCategory(), postDTO.getPrice());
        seller.addPost(post);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPostsBy(int userId, String order) {
        Seller seller = (Seller) sellerService.findUserById(userId);
        List<Seller> sellers = relationService.getSellers(seller);
        List<Post> posts = sellers.stream()
                .flatMap(s -> s.getPosts().stream())
                .filter(p -> p.getDate().after(Date.from(Instant.now().minus(14, ChronoUnit.DAYS))))
                .collect(Collectors.toList());
        PostsResponseDTO response = new PostsResponseDTO(seller.getUserId(),
                Manager.orderPostsBy(order, posts)
                        .stream().map(p-> new PostResponseDTO(p.getDate(),p.getDetail(),p.getCategory().getValue(),p.getPrice())).collect(Collectors.toList())
        );
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity newPromoPost(PromoPostRequestDTO postDTO) {
        Seller seller = (Seller) sellerService.findUserById(postDTO.getUserId());
        Post post = new Post(postDTO.getDate(), postDTO.getDetail(), postDTO.getCategory(), postDTO.getPrice(), postDTO.isHasPromo(), postDTO.getDiscount());
        seller.addPost(post);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPromoQuantity(int userId) {
        Seller seller = (Seller) sellerService.findUserById(userId);
        List<Post> posts = seller.getPosts().stream().filter(p-> p.isHasPromo()).collect(Collectors.toList());
        PromoPostsCountResponseDTO response = new PromoPostsCountResponseDTO(seller, posts.size());
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPromoList(int userId) {
        Seller seller = (Seller) sellerService.findUserById(userId);
        List<Post> posts = seller.getPosts().stream().filter(p-> p.isHasPromo()).collect(Collectors.toList());
        PromoPostsListResponseDTO response = new PromoPostsListResponseDTO(seller,
                posts.stream().map(p-> new PostPromoResponseDTO(p.getDate(),p.getDetail(),p.getCategory().getValue(),p.getPrice(),p.isHasPromo(),p.getDiscount())).collect(Collectors.toList())
        );
        return new ResponseEntity(response,HttpStatus.OK);
    }

}
