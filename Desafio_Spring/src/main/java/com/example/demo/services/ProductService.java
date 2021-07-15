package com.example.demo.services;

import com.example.demo.cache.CacheConfiguration;
import com.example.demo.dtos.FollowedProductListResponse;
import com.example.demo.dtos.FollowerListResponseDTO;
import com.example.demo.dtos.PostDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private CacheConfiguration cacheConfiguration;

    Random random = new Random();

    public void createPost(PostDTO postDTO){
        int postId = Math.abs(random.nextInt());
        int postUserId = postDTO.getUserId();
        postDTO.setIdPost(postId);
        User postUser = cacheConfiguration.userCache.getIfPresent(postUserId);
        if(postUser != null){
            cacheConfiguration.postCache.put(postId, postDTO); // only valid users can publish a product
            postUser.getPosts().add(postDTO); // add the product's post to User
        } else {
            throw new UserNotFoundException(postUserId);
        }
    }

    public ResponseEntity<FollowedProductListResponse> getProductsPublishedByFollowedOnLastTwoWeeks(int userId, String order){
        User user = cacheConfiguration.userCache.getIfPresent(userId);
        List<PostDTO> postList = new ArrayList<>();
        if(user != null){
            for(int i = 0; i < user.getFollowed().size(); i++){
                UserDTO userDTO = user.getFollowed().get(i);
                int sellerId = userDTO.getUserId();
                User seller = cacheConfiguration.userCache.getIfPresent(sellerId);
                if(seller != null) {
                    LocalDate currentDate = LocalDate.now();
                    LocalDate expectedDate = currentDate.minusWeeks(2);
                    List<PostDTO> sellerLast2WeeksPosts = seller.getPosts().stream()
                            .filter(p ->
                                    p.getDate().isAfter(expectedDate) && p.getDate().isBefore(currentDate) || p.getDate().isEqual(currentDate) || p.getDate().isEqual(expectedDate)
                            ).collect(Collectors.toList());
                    postList.addAll(sellerLast2WeeksPosts);
                } else {
                    throw new UserNotFoundException(sellerId);
                }
            }
        } else {
            throw new UserNotFoundException(userId);
        }
        if (order.equals("date_asc")){
            postList.sort(Comparator.comparing(PostDTO::getDate));
        } else if (order.equals("date_desc")) {
            postList.sort(Comparator.comparing(PostDTO::getDate).reversed());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new FollowedProductListResponse(userId, postList));
    }
}
