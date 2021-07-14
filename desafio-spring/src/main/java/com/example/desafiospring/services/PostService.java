package com.example.desafiospring.services;

import com.example.desafiospring.dto.request.PostRequestDTO;
import com.example.desafiospring.dto.request.ProductRequestDTO;
import com.example.desafiospring.dto.response.CountPromoPostsResponseDTO;
import com.example.desafiospring.dto.response.PostResponseDTO;
import com.example.desafiospring.dto.response.ProductResponseDTO;
import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.models.Post;
import com.example.desafiospring.models.Product;
import com.example.desafiospring.models.Seller;
import com.example.desafiospring.models.User;
import com.example.desafiospring.repositories.IPostRepository;
import com.example.desafiospring.repositories.IProductRepository;
import com.example.desafiospring.repositories.ISellerRepository;
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

    private static final Map<String, Comparator<Post>> ORDER_PARAMS = Map.of(
            "date_asc", Comparator.comparing(Post::getDate),
            "date_desc", Comparator.comparing(Post::getDate).reversed(),
            "id", Comparator.comparing(Post::getIdPost)
    );

    public PostService(IPostRepository postRepository, IProductRepository productRepository, ISellerRepository sellerRepository) {
        this.postRepository = postRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Post getPost(Long id) throws PostException {
        this.postRepository.findById(id);
        return null;
    }

    @Override
    public void savePost(@Valid @RequestBody PostRequestDTO postRequestDTO) throws PostException, SellerException {
        Seller seller = this.sellerRepository.findById(postRequestDTO.getUserId());
        ProductRequestDTO productRequestDTO = postRequestDTO.getDetail();
        Product product = new Product(
                null,
                productRequestDTO.getProductName(),
                productRequestDTO.getType(),
                productRequestDTO.getBrand(),
                productRequestDTO.getColor(),
                productRequestDTO.getNotes()
        );
        Long productId = this.productRepository.addProduct(product);
        Post post = new Post(seller.getUserId(), null, postRequestDTO.getDate(), productId, postRequestDTO.getCategory(), postRequestDTO.getPrice(), postRequestDTO.getHasPromo(), postRequestDTO.getDiscount());
        this.postRepository.addPost(post);
    }

    @Override
    public List<PostResponseDTO> getFollowedPostsByUser(Long userId, String order) {
        List<Seller> sellers = this.sellerRepository.findByFollowerUserId(userId);
        List<Long> sellersIds = sellers.stream().map(User::getUserId).collect(Collectors.toList());
        List<Post> posts = this.postRepository.findBySellersIds(sellersIds);

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, -14);
        Date previousDate = c.getTime();

        List<Post> postsFilteredBySellersAndDate = this.postRepository.getPostAfterDateBySellersIds(sellersIds, previousDate);
        List<Post> postsOrdered = postsFilteredBySellersAndDate.stream().sorted(ORDER_PARAMS.getOrDefault(order, Comparator.comparing(Post::getIdPost))).collect(Collectors.toList());
        List<Long> productsIdsInFilteredPosts = postsOrdered.stream().map(Post::getProductId).collect(Collectors.toList());
        List<Product> products = new ArrayList<>(this.productRepository.findByIds(productsIdsInFilteredPosts));
        List<PostResponseDTO> postResponseDTOs;
        if (posts.isEmpty()) {
            postResponseDTOs = List.of();
        } else {
            List<ProductResponseDTO> productResponseDTOs = products.stream().map(p -> new ProductResponseDTO(p.getProductId(), p.getProductName(), p.getType(), p.getBrand(), p.getColor(), p.getNotes())).collect(Collectors.toList());
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
                                    post.getPrice()))
                    .collect(Collectors.toList());
        }
        return postResponseDTOs;
    }

    @Override
    public CountPromoPostsResponseDTO countPromoPostsPerSeller(Long userId) throws SellerException {
        Seller seller = this.sellerRepository.findById(userId);
        List<Post> posts = this.postRepository.findPromoPostsBySellerId(userId);
        CountPromoPostsResponseDTO responseDTO = new CountPromoPostsResponseDTO(seller.getUserId(), seller.getUserName(), posts.size());
        return responseDTO;
    }

    @Override
    public List<PostResponseDTO> getPromoPostsPerSeller(Long userId) throws SellerException {
        Seller seller = this.sellerRepository.findById(userId);
        List<Post> posts = this.postRepository.findPromoPostsBySellerId(seller.getUserId());
        List<PostResponseDTO> responseDTOs;
        if (posts.isEmpty()) {
            responseDTOs = List.of();
        } else {
            List<Long> productsIdsInPromoPosts = posts.stream().map(Post::getProductId).collect(Collectors.toList());
            List<Product> products = new ArrayList<>(this.productRepository.findByIds(productsIdsInPromoPosts));
            List<ProductResponseDTO> productResponseDTOs = products.stream().map(p -> new ProductResponseDTO(p.getProductId(), p.getProductName(), p.getType(), p.getBrand(), p.getColor(), p.getNotes())).collect(Collectors.toList());
            responseDTOs = posts
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
        }
        return responseDTOs;
    }
}
