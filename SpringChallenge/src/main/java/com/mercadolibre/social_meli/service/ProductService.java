package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.dto.request.PromoProductRequestDTO;
import com.mercadolibre.social_meli.dto.response.*;
import com.mercadolibre.social_meli.exception.InvalidValueException;
import com.mercadolibre.social_meli.helper.DateValidator;
import com.mercadolibre.social_meli.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private static final Integer RECENT_DAYS = 14;
    private final IProductRepository productRepository;
    private final IUserService userService;

    public ProductService(IProductRepository productRepository, IUserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public void postNewProduct(ProductRequestDTO productData) {
        // this is used as a check for user existence. Throws a runtime exception when no user is found.
        this.userService.getUserById(productData.getUserId());
        if (DateValidator.isValid(productData.getDate())) {
            this.productRepository.saveProduct(productData);
        } else {
            throw new InvalidValueException("Date must be in dd-MM-yyyy format");
        }
    }

    @Override
    public void postNewPromoProduct(PromoProductRequestDTO productData) {
        // this is used as a check for user existence. Throws a runtime exception when no user is found.
        this.userService.getUserById(productData.getUserId());
        if (DateValidator.isValid(productData.getDate()) && productData.getHasPromo()) {
            this.productRepository.saveProduct(productData);
        } else {
            var errorMessage = productData.getHasPromo() ? "Date must be in dd-MM-yyyy format" : "Field hasPromo needs to be true";
            throw new InvalidValueException(errorMessage);
        }
    }

    @Override
    public FollowedPostsResponseDTO getFollowedRecentPosts(Integer userId, String order) {
        var followedIds = this.userService.getFollowed(userId, null)
                .getFollowed().stream().map(UserResponseDTO::getUserId).collect(Collectors.toList());

        var postOrder = Objects.isNull(order) ? "date_desc" : order;
        var userPosts = this.productRepository.getMultipleUsersPost(followedIds, postOrder);

        var followedPosts = new FollowedPostsResponseDTO();
        followedPosts.setPosts(
                userPosts.stream().map(post -> new ProductResponseDTO(post.getId_post(), post.getDate(), post.getDetail(), post.getCategory(), post.getPrice()))
                .collect(Collectors.toList())
        );
        if (postOrder.equals("date_desc")) {
            followedPosts.setPosts(this.filterRecentPosts(followedPosts.getPosts()));
        }

        followedPosts.setUserId(userId);
        return followedPosts;
    }

    @Override
    public PromoCountResponseDTO getUserPromoCount(Integer userId) {
        var user = this.userService.getUserById(userId);
        var promoPosts = this.productRepository.getUserPromoPosts(userId, null);

        return new PromoCountResponseDTO(user.getUserId(), user.getUserName(), promoPosts.size());
    }

    @Override
    public UserPromoPostsResponseDTO getUserPromoPosts(Integer userId, String order) {
        var user = this.userService.getUserById(userId);
        var promoPosts = this.productRepository.getUserPromoPosts(userId, order);
        return new UserPromoPostsResponseDTO(user.getUserId(), user.getUserName(), promoPosts);
    }

    private List<ProductResponseDTO> filterRecentPosts(List<ProductResponseDTO> posts) {
        var today = LocalDate.now();
        var dateFormatter = DateValidator.getDateTimeFormatter();
        return posts.stream().filter(
                p -> ChronoUnit.DAYS.between(LocalDate.parse(p.getDate(), dateFormatter), today) <= RECENT_DAYS
        ).collect(Collectors.toList());
    }

}
