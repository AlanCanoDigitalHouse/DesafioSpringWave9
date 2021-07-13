package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.BuyerDTO;
import com.mercadolibre.socialmeli.dtos.PostDTO;
import com.mercadolibre.socialmeli.dtos.SellerPostListDTO;
import com.mercadolibre.socialmeli.exceptions.RecordNotFoundException;
import com.mercadolibre.socialmeli.repositories.interfaces.ProductRepository;
import com.mercadolibre.socialmeli.repositories.interfaces.UserRepository;
import com.mercadolibre.socialmeli.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createPost(PostDTO postDTO) {
        userRepository.getSellerById(postDTO.getUserId());
        productRepository.createPost(postDTO);
    }

    @Override
    public SellerPostListDTO twoWeeksSellerPosts(Integer userId, String order) throws RecordNotFoundException {
        BuyerDTO buyer = userRepository.getBuyerById(userId);
        // iterate sellers and collect posts
        List<PostDTO> postList = new LinkedList<>();
        Instant since = ZonedDateTime.now().minusWeeks(2).toInstant();
        buyer.getFollowed().forEach(seller -> {
            List<PostDTO> sellerPostList = productRepository.getPostsBySeller(seller.getUserId()).stream()
                    .filter(p -> p.getDate().toInstant().isAfter(since))
                    .collect(Collectors.toList());

            postList.addAll(sellerPostList);
        });
        sortPostList(postList, order);

        // build dto
        SellerPostListDTO dto = new SellerPostListDTO();
        dto.setUserId(buyer.getUserId());
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
}
