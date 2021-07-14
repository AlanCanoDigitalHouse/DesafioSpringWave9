package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.*;
import com.mercadolibre.socialmeli.dtos.resp.FollowedPostsDTO;
import com.mercadolibre.socialmeli.dtos.resp.SellerPostsPromoDTO;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;
import com.mercadolibre.socialmeli.repositories.interfaces.ProductRepository;
import com.mercadolibre.socialmeli.services.interfaces.ProductService;
import com.mercadolibre.socialmeli.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final UserService userService;
    private final ProductRepository productRepository;

    public ProductServiceImpl(UserService userService, ProductRepository productRepository) {
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Override
    public void createPost(PostDTO postDTO) throws SellerNotFoundException {
        userService.getSellerById(postDTO.getUserId());
        productRepository.createPost(postDTO);
    }

    @Override
    public FollowedPostsDTO followedSellersPostsLastTwoWeeks(Integer userId, String order) throws BuyerNotFoundException {
        BuyerDTO buyer = userService.getBuyerById(userId);
        // iterate sellers and collect posts
        List<PostDTO> postList = new LinkedList<>();
        Instant since = ZonedDateTime.now().minusWeeks(2).toInstant();
        buyer.getFollowed().forEach(seller -> {
            List<PostDTO> sellerPostList = productRepository.getPostsBySeller(seller.getUserId()).stream()
                    .filter(p -> p.getDate().toInstant().isAfter(since))
                    .map(PostDTO::clonePostDTO)    // clone object to change pointer reference
                    .peek(p -> p.setUserId(null))  // set userId to null (to not show it in the response)
                    .collect(Collectors.toList());

            postList.addAll(sellerPostList);
        });
        sortPostList(postList, order);

        // build dto
        FollowedPostsDTO dto = new FollowedPostsDTO(buyer.getUserId());
        dto.setPosts(postList);
        return dto;
    }

    private void sortPostList(List<PostDTO> postList, String order) {
        if(Objects.nonNull(order)) {
            if("date_asc".equals(order)) {
                postList.sort(Comparator.comparing(PostDTO::getDate));
            } else if("date_desc".equals(order)){
                postList.sort(Comparator.comparing(PostDTO::getDate).reversed());
            }
        }
    }

    @Override
    public SellerPostsPromoDTO getPromoProductsCount(Integer sellerId) throws SellerNotFoundException {
        SellerDTO seller = userService.getSellerById(sellerId);
        List<PostPromoDTO> promoPostList = productRepository.getPromoPostsBySeller(sellerId);

        SellerPostsPromoDTO dto = new SellerPostsPromoDTO(seller);
        dto.setPromoproducts_count(promoPostList.size());
        return dto;
    }

    @Override
    public SellerPostsPromoDTO getPromoPosts(Integer sellerId) throws SellerNotFoundException {
        SellerDTO seller = userService.getSellerById(sellerId);
        List<PostPromoDTO> promos = productRepository.getPromoPostsBySeller(sellerId);

        promos = promos.stream()
                .map(PostPromoDTO::clonePostPromoDTO)
                .peek(p -> p.setUserId(null))
                .collect(Collectors.toList());

        SellerPostsPromoDTO dto = new SellerPostsPromoDTO(seller);
        dto.setPosts(promos);
        return dto;
    }

}
