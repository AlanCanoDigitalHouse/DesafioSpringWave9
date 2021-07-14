package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.*;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;
import com.mercadolibre.socialmeli.repositories.interfaces.ProductRepository;
import com.mercadolibre.socialmeli.repositories.interfaces.UserRepository;
import com.mercadolibre.socialmeli.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;
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
    public void createPost(PostDTO postDTO) throws SellerNotFoundException {
        userRepository.getSellerById(postDTO.getUserId());
        productRepository.createPost(postDTO);
    }

    @Override
    public SellerPostListDTO twoWeeksSellerPosts(Integer userId, String order) throws BuyerNotFoundException {
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

    @Override
    public PromoProductsCountDTO getPromoProductsCount(Integer sellerId) throws SellerNotFoundException {
        SellerDTO seller = userRepository.getSellerById(sellerId);
        List<PromoPostDTO> promoPostList = productRepository.getPromoPostsBySeller(sellerId);

        List<ProductDTO> productList = promoPostList.stream()
                .map(PostDTO::getDetail)
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ProductDTO::getProduct_id))),
                        LinkedList::new));

//        List<ProductDTO> productList = promoPostList.stream()
//                .map(PostDTO::getDetail)
//                .collect(Collectors.toList());

        PromoProductsCountDTO dto = new PromoProductsCountDTO();
        dto.setUserId(seller.getUserId());
        dto.setUserName(seller.getUserName());
        dto.setPromoproducts_count(productList.size());
        return dto;
    }

    @Override
    public SellerPromoPostListDTO getPromoPosts(Integer sellerId) throws SellerNotFoundException {
        SellerDTO seller = userRepository.getSellerById(sellerId);
        List<PromoPostDTO> promos = productRepository.getPromoPostsBySeller(sellerId);

        SellerPromoPostListDTO dto = new SellerPromoPostListDTO(seller);
        dto.setPosts(promos);
        return dto;
    }

}
