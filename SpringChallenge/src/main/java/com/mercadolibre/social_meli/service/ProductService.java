package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.dto.response.FollowedPostsResponseDTO;
import com.mercadolibre.social_meli.dto.response.ProductResponseDTO;
import com.mercadolibre.social_meli.dto.response.UserResponseDTO;
import com.mercadolibre.social_meli.exception.InvalidQueryParamException;
import com.mercadolibre.social_meli.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final IUserService userService;

    public ProductService(IProductRepository productRepository, IUserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public void postNewProduct(ProductRequestDTO productData) {
        this.productRepository.saveProduct(productData);
    }

    @Override
    public FollowedPostsResponseDTO getFollowedRecentPosts(Integer userId, String order) {
        var followedIds = this.userService.getFollowed(userId, null)
                .getFollowed().stream().map(UserResponseDTO::getUserId).collect(Collectors.toList());

        var followedPosts = new FollowedPostsResponseDTO();
        followedPosts.setUserId(userId);
        for (Integer id:followedIds) {
            this.productRepository.getUserPosts(id)
                    .stream().map(post -> new ProductResponseDTO(post.getId_post(), post.getDate(), post.getDetail(), post.getCategory(), post.getPrice()))
                    .collect(Collectors.toList())
                    .forEach(followedPosts.getPosts()::add);
        }
        followedPosts.setPosts(this.filterRecentPosts(followedPosts.getPosts(), 14));

        if (Objects.nonNull(order)) {
            this.sortPostDates(followedPosts.getPosts(), order);
        }

        return followedPosts;
    }

    private List<ProductResponseDTO> filterRecentPosts(List<ProductResponseDTO> posts, Integer days) {
        var today = LocalDate.now();
        var dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return posts.stream().filter(
                p -> ChronoUnit.DAYS.between(LocalDate.parse(p.getDate(), dateFormatter), today) < days
        ).collect(Collectors.toList());
    }

    private void sortPostDates(List<ProductResponseDTO> posts, String order) {
        var dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        switch (order) {
            case "date_asc":
                posts.sort(Comparator.comparing(p -> LocalDate.parse(p.getDate(), dateFormatter)));
                break;
            case "date_desc":
                posts.sort(Comparator.comparing(p -> LocalDate.parse(p.getDate(), dateFormatter)));
                Collections.reverse(posts);
                break;
            default:
                throw new InvalidQueryParamException("Order param must be date_asc or date_desc");
        }
    }

}
