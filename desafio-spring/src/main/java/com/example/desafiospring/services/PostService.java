package com.example.desafiospring.services;

import com.example.desafiospring.dto.request.PostRequestDTO;
import com.example.desafiospring.dto.request.ProductRequestDTO;
import com.example.desafiospring.dto.response.CountPromoPostsResponseDTO;
import com.example.desafiospring.dto.response.PostResponseDTO;
import com.example.desafiospring.dto.response.ProductResponseDTO;
import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.exceptions.UserException;
import com.example.desafiospring.models.Post;
import com.example.desafiospring.models.Product;
import com.example.desafiospring.models.Seller;
import com.example.desafiospring.models.User;
import com.example.desafiospring.repositories.IPostRepository;
import com.example.desafiospring.repositories.IProductRepository;
import com.example.desafiospring.repositories.ISellerRepository;
import com.example.desafiospring.repositories.IUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    IPostRepository postRepository;
    IProductRepository productRepository;
    ISellerRepository sellerRepository;
    IUserRepository userRepository;
    ObjectMapper mapper;

    private static final Map<String, Comparator<Post>> ORDER_PARAMS = Map.of(
            "date_asc", Comparator.comparing(Post::getDate),
            "date_desc", Comparator.comparing(Post::getDate).reversed()
    );

    /**
     * @param postRepository
     * @param productRepository
     * @param sellerRepository
     * @param userRepository
     * @param mapper
     */
    public PostService(IPostRepository postRepository, IProductRepository productRepository, ISellerRepository sellerRepository, IUserRepository userRepository, ObjectMapper mapper) {
        this.postRepository = postRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    /**
     * @param postRequestDTO
     * @throws PostException
     * @throws SellerException
     */
    @Override
    public void savePost(@Valid @RequestBody PostRequestDTO postRequestDTO) throws PostException, SellerException {
        Seller seller = this.sellerRepository.findById(postRequestDTO.getUserId());
        ProductRequestDTO productRequestDTO = postRequestDTO.getDetail();
        Product product = mapper.convertValue(productRequestDTO, Product.class);
        Long productId = this.productRepository.addProduct(product);
        Post post = new Post(
                seller.getUserId(),
                null,
                postRequestDTO.getDate(),
                productId,
                postRequestDTO.getCategory(),
                postRequestDTO.getPrice());
        this.postRepository.addPost(post);
    }

    /**
     * @param postRequestDTO
     * @throws PostException
     * @throws SellerException
     */
    @Override
    public void savePromoPost(@Valid @RequestBody PostRequestDTO postRequestDTO) throws PostException, SellerException {
        if (postRequestDTO.getHasPromo() == null)
            throw new PostException(PostException.MISSING_HAS_PROMO_FIELD);
        else if (postRequestDTO.getDiscount() == null)
            throw new PostException(PostException.MISSING_DISCOUNT_FIELD);

        Seller seller = this.sellerRepository.findById(postRequestDTO.getUserId());
        ProductRequestDTO productRequestDTO = postRequestDTO.getDetail();
        Product product = mapper.convertValue(productRequestDTO, Product.class);
        Long productId = this.productRepository.addProduct(product);
        Post post = new Post(
                seller.getUserId(),
                null,
                postRequestDTO.getDate(),
                productId,
                postRequestDTO.getCategory(),
                postRequestDTO.getPrice(),
                postRequestDTO.getHasPromo(),
                postRequestDTO.getDiscount());
        this.postRepository.addPost(post);
    }

    /**
     * @param userId
     * @param order
     * @return List<PostResponseDTO>
     */
    @Override
    public List<PostResponseDTO> getFollowedPostsByUser(Long userId, String order) throws UserException {
        this.userRepository.checkIfUserExistsById(userId);
        List<Seller> sellers = this.sellerRepository.findByFollowerUserId(userId);
        List<Long> sellersIds = sellers.stream().map(User::getUserId).collect(Collectors.toList());

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, -14);
        Date previousDate = c.getTime();

        List<Post> postsFilteredBySellersAndDate = this.postRepository.getPostAfterDateBySellersIds(sellersIds, previousDate);
        List<Post> postsOrdered = postsFilteredBySellersAndDate.stream().sorted(ORDER_PARAMS.getOrDefault(order, Comparator.comparing(Post::getDate).reversed())).collect(Collectors.toList());
        List<Long> productsIdsInFilteredPosts = postsOrdered.stream().map(Post::getProductId).collect(Collectors.toList());
        List<Product> products = new ArrayList<>(this.productRepository.findByIds(productsIdsInFilteredPosts));
        List<PostResponseDTO> postResponseDTOs;
        if (postsFilteredBySellersAndDate.isEmpty()) {
            postResponseDTOs = List.of();
        } else {
            postResponseDTOs = getPostResponseDTOs(postsOrdered, products);
        }
        return postResponseDTOs;
    }

    /**
     * @param userId
     * @return CountPromoPostsResponseDTO
     * @throws SellerException
     */
    @Override
    public CountPromoPostsResponseDTO countPromoPostsPerSeller(Long userId) throws SellerException {
        Seller seller = this.sellerRepository.findById(userId);
        List<Post> posts = this.postRepository.findPromoPostsBySellerId(userId);
        CountPromoPostsResponseDTO responseDTO = new CountPromoPostsResponseDTO(seller.getUserId(), seller.getUserName(), posts.size());
        return responseDTO;
    }

    /**
     * @param sellerId
     * @return List<PostResponseDTO>
     * @throws SellerException
     */
    @Override
    public List<PostResponseDTO> getPromoPostsPerSeller(Long sellerId) throws SellerException {
        this.sellerRepository.checkIfSellerExistsById(sellerId);
        List<Post> posts = this.postRepository.findPromoPostsBySellerId(sellerId);
        List<PostResponseDTO> responseDTOs;
        if (posts.isEmpty()) {
            responseDTOs = List.of();
        } else {
            List<Long> productsIdsInPromoPosts = posts.stream().map(Post::getProductId).collect(Collectors.toList());
            List<Product> products = new ArrayList<>(this.productRepository.findByIds(productsIdsInPromoPosts));
            responseDTOs = getPostResponseDTOs(posts, products);
        }
        return responseDTOs;
    }

    /**
     * @param postsOrdered
     * @param products
     * @return List<PostResponseDTO>
     */
    private List<PostResponseDTO> getPostResponseDTOs(List<Post> postsOrdered, List<Product> products) {
        List<PostResponseDTO> postResponseDTOs;
        List<ProductResponseDTO> productResponseDTOs = products.stream().map(p -> mapper.convertValue(p, ProductResponseDTO.class)).collect(Collectors.toList());
        postResponseDTOs = postsOrdered
                .stream()
                .map(
                        post -> new PostResponseDTO(
                                post.getUserId(),
                                post.getIdPost(),
                                post.getDate(),
                                productResponseDTOs
                                        .stream()
                                        .filter(product -> post.getProductId().equals(product.getProductId()))
                                        .findAny()
                                        .get(),
                                post.getCategory(),
                                post.getPrice(),
                                post.getHasPromo(),
                                post.getDiscount()))
                .collect(Collectors.toList());
        return postResponseDTOs;
    }
}
