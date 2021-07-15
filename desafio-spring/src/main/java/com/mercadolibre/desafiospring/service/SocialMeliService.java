package com.mercadolibre.desafiospring.service;

import com.mercadolibre.desafiospring.dto.request.*;
import com.mercadolibre.desafiospring.dto.response.*;
import com.mercadolibre.desafiospring.exception.*;
import com.mercadolibre.desafiospring.exception.post.*;
import com.mercadolibre.desafiospring.exception.user.*;
import com.mercadolibre.desafiospring.model.*;
import com.mercadolibre.desafiospring.repository.*;
import com.mercadolibre.desafiospring.util.SortUtils;
import org.springframework.stereotype.*;

import java.util.Comparator;
import java.util.Map;

@Service
public class SocialMeliService {
    private UsersRepository usersRepository;
    private CategoryRepository categoryRepository;

    public SocialMeliService(UsersRepository usersRepository,
                             CategoryRepository categoryRepository) {
        this.usersRepository = usersRepository;
        this.categoryRepository = categoryRepository;
    }

    public UserDTOResponse createClient(UserDTO client)
            throws Exception {
        this.validateUserDTO(client);
        this.usersRepository.addClient(client.getUserId(),
                client.getUserName());
        return new UserDTOResponse(
                this.usersRepository.getClient(client.getUserId()));
    }

    public UserDTOResponse createSeller(UserDTO seller) throws Exception {
        this.validateUserDTO(seller);
        this.usersRepository.addSeller(seller.getUserId(),
                seller.getUserName());
        return new UserDTOResponse(this.usersRepository.
                getSeller(seller.getUserId()));
    }

    public void makeUserFollowSeller(int userId, int userIdToFollow)
            throws Exception {
        this.usersRepository.makeUserFollowSeller(userId, userIdToFollow);
    }

    public void makeUserUnfollowSeller(int userId, int userIdToUnfollow)
            throws Exception {
        this.usersRepository.makeUserUnfollowSeller(userId, userIdToUnfollow);
    }

    public FollowersCountResponse getNumberOfFollowersOf(int userId)
            throws SellerDoesNotExistsException {
        return new FollowersCountResponse(
                this.usersRepository.numberOfFollowersOf(userId),
                this.usersRepository.getSeller(userId));
    }

    public FollowerListResponse getListOfFollowersOf(int userId, String order)
            throws SellerDoesNotExistsException, InvalidSortCriteriaException {
        Seller seller = this.usersRepository.getSeller(userId);
        this.validateUserSortingOrder(order);
        return new FollowerListResponse(seller, order);
    }

    public FollowingListResponse getListOfFollowedBy(int userId, String order)
            throws UserDoesNotExistsException, InvalidSortCriteriaException {
        Client client = this.usersRepository.getClient(userId);
        this.validateUserSortingOrder(order);
        return new FollowingListResponse(client, order);
    }

    public SellerLastPostsResponse getFollowedLastsPosts
            (int userId, String order)
            throws UserDoesNotExistsException, InvalidSortCriteriaException {
        this.validatePostSortingOrder(order);
        order = order == null ?
                SortUtils.DATE_DESC_ORDER_QUERY_PARAM :
                order;

        return new SellerLastPostsResponse(userId,
                this.usersRepository.getFollowedLastsPosts(userId, order));
    }

    public void validatePostSortingOrder(String order)
            throws InvalidSortCriteriaException {
        if (order != null && Post.sortings.getOrDefault(order, null) == null) {
            throw new InvalidSortCriteriaException();
        }
    }

    public void validateUserSortingOrder(String order)
            throws InvalidSortCriteriaException {
        if (order != null && User.sortings.getOrDefault(order, null) == null) {
            throw new InvalidSortCriteriaException();
        }
    }

    public void validateUserDTO(UserDTO user) throws Exception {
        if (user == null) {
            throw new InvalidUserException("User is not valid.");
        }

        if (user.getUserId() <= 0) {
            throw new InvalidUserException("User id is not valid.");
        }

        if (user.getUserName() == null ||
                user.getUserName().length() == 0) {
            throw new InvalidUserException("User name is not valid.");
        }
    }

    public void validatePostDTO(PostDTO post) throws Exception {
        if (post == null) {
            throw new InvalidPostException("Post is not valid.");
        }

        if (post.getUserId() <= 0) {
            throw new InvalidUserException(
                    "User id on the post is not valid.");
        }

        if (post.getDate() == null) {
            throw new InvalidPostException(
                    "Date on the post is not valid.");
        }

        if (post.getCategory() <= 0) {
            throw new InvalidPostException(
                    "Post category is not valid.");
        }

        if (post.getPrice() <= 0) {
            throw new InvalidPostException(
                    "Post price is invalid.");
        }

        if (post.getDetail() == null) {
            throw new InvalidPostException(
                    "Missing product on the post");
        }
    }

    public void validateProductDTO(ProductDTO product) throws InvalidProductException {
        if (product == null) {
            throw new InvalidProductException(
                    "Product is not valid.");
        }

        if (product.getProductName() == null ||
                product.getProductName().length() == 0) {
            throw new InvalidProductException(
                    "Invalid product name.");
        }

        if (product.getType() == null ||
                product.getType().length() == 0) {
            throw new InvalidProductException(
                    "Invalid product type.");
        }

        if (product.getBrand() == null ||
                product.getBrand().length() == 0) {
            throw new InvalidProductException(
                    "Invalid product brand");
        }

        if (product.getColor() == null ||
                product.getColor().length() == 0) {
            throw new InvalidProductException(
                    "Invalid product color");
        }

        if (product.getNotes() == null ||
                product.getNotes().length() == 0) {
            throw new InvalidProductException(
                    "Invalid product notes.");
        }
    }

    private void validatePromoPostDTO(PromoPostDTO promoPost) throws Exception {
        this.validatePostDTO(promoPost);

        if (promoPost.getHasPromo() == null) {
            throw new InvalidPostException("'Has promo' is not valid.");
        }

        if (promoPost.getDiscount() <= 0 || promoPost.getDiscount() > 1) {
            throw new InvalidPostException("Discount is not valid");
        }
    }

    public void createProductsCategory(int idCategory) throws InvalidCategoryException {
        if (idCategory <= 0) {
            throw new InvalidCategoryException();
        }

        this.categoryRepository.addCategory(idCategory);
    }

    public void createPost(PostDTO post) throws Exception {
        this.validatePostDTO(post);
        ProductDTO product = post.getDetail();
        this.validateProductDTO(product);
        this.addPostToSeller(post, product);
    }

    public void addPostToSeller(PostDTO post, ProductDTO product)
            throws Exception {
        this.usersRepository.addPostToSeller(post.getUserId(),
                this.categoryRepository.getPostRepository(
                        post.getCategory()).addPost(
                        post.getDate(),
                        post.getCategory(), post.getPrice(),
                        product.getProductName(),
                        product.getType(), product.getBrand(),
                        product.getColor(), product.getNotes()));
    }

    public void addPromoPostToSeller(PromoPostDTO promoPost, ProductDTO product)
            throws Exception {
        this.usersRepository.addPromoPostToSeller(promoPost.getUserId(),
                this.categoryRepository.getPostRepository(
                        promoPost.getCategory()).addPromoPost(
                        promoPost.getDate(), promoPost.getCategory(),
                        promoPost.getPrice(), promoPost.getDiscount(),
                        product.getProductName(),
                        product.getType(), product.getBrand(),
                        product.getColor(), product.getNotes()));
    }

    public void createPromoPost(PromoPostDTO promoPost) throws Exception {
        this.validatePromoPostDTO(promoPost);
        ProductDTO product = promoPost.getDetail();
        this.validateProductDTO(product);

        if (promoPost.getHasPromo()) {
            this.addPromoPostToSeller(promoPost, product);
        } else {
            this.addPostToSeller(promoPost, product);
        }
    }

    public PromoPostCountResponse getPromoPostNumberOf(int userId)
            throws SellerDoesNotExistsException {
        return new PromoPostCountResponse(userId,
                this.usersRepository.getSeller(userId).getName(),
                this.usersRepository.getPromoPostNumberOf(userId));
    }

    public PromoPostListResponse getPromoPostListOf(int userId, String order)
            throws SellerDoesNotExistsException, InvalidSortCriteriaException {
        this.validatePostSortingOrder(order);
        Seller seller = this.usersRepository.getSeller(userId);
        return new PromoPostListResponse(userId,
                seller.getName(), (order == null) ? seller.getPromoPosts() :
                seller.getSortedPromoPosts(order));
    }
}
