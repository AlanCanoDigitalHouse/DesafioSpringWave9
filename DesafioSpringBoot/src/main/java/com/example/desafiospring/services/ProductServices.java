package com.example.desafiospring.services;

import com.example.desafiospring.dtos.request.PostPromoRequestDto;
import com.example.desafiospring.dtos.request.PostRequestDto;
import com.example.desafiospring.dtos.response.PostResponseDto;
import com.example.desafiospring.dtos.response.ProductResponseDto;
import com.example.desafiospring.dtos.response.UserResponseDto;
import com.example.desafiospring.exceptions.LogicValidationException;
import com.example.desafiospring.repositories.ProductRepository;
import com.example.desafiospring.utils.Factory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServices implements Sorter<PostResponseDto> {
    private ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
        productRepository.setPosts(Factory.generatePost());
    }
    /**
     * Create a new Post
     * @param postResponseDto object Post
     */
    public void createNewPost(PostRequestDto postResponseDto) {
        var productRequest = postResponseDto.getDetail();
        var product = new ProductResponseDto(productRequest);
        var post = new PostResponseDto(postResponseDto.getUserId(),
                postResponseDto.getDate(), postResponseDto.getCategory(),
                postResponseDto.getPrice(), product);
        productRepository.create(post);
    }

    /**
     * Create a new Post with the property HasPromo = true
     * @param promoRequestDto object PromoPost
     */
    public void createNewPromoPost(PostPromoRequestDto promoRequestDto){
        var productRequest = promoRequestDto.getDetail();
        var product = new ProductResponseDto(productRequest);
        var post = new PostResponseDto(promoRequestDto.getUserId(),
                promoRequestDto.getDate(), promoRequestDto.getCategory(),
                promoRequestDto.getPrice(), product);
        post.setHasPromo(promoRequestDto.getHasPromo());
        post.setDiscount(promoRequestDto.getDiscount());
        productRepository.create(post);
    }

    /**
     * Get all post from followed Sellers
     * @param listFollowed<UserResponse> list of sellers that a certain user follows
     * @return list of post from all the received sellers
     */
    public List<PostResponseDto> getPosts(List<UserResponseDto> listFollowed) {
        List<PostResponseDto> posts = new ArrayList<>();
        for (UserResponseDto user : listFollowed) {
            var sellerId = user.getId();
            var postFromSeller = productRepository.getPosts().stream()
                    .filter(postResponseDto -> postResponseDto.getUserId() == sellerId)
                    .collect(Collectors.toList());
            posts.addAll(postFromSeller);
        }
        return posts;
    }

    /**
     * Get the first post from an user
     * @param userId Integer userId
     * @return the first post of an user
     */
    public PostResponseDto getPost(Integer userId) {
        var post = productRepository.getPosts().stream()
                .filter(postResponseDto -> postResponseDto.getUserId() == userId).findFirst();
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new LogicValidationException("This user is not a seller or may not have any post");
        }
    }

    /**
     * Get al list of all the promo Post that belong to an user
     * @param userId Integer userId
     * @return the list with all post with the property hasPromo = true
     */
    public List<PostResponseDto> getPromoPost(Integer userId){
        return productRepository.getPosts().stream()
                .filter( postResponseDto -> postResponseDto.getUserId() == userId &&
                        postResponseDto.getHasPromo() )
                .collect(Collectors.toList());
    }
    /**
     * Sort a list descending accord of its date
     * @param list<PostResponseDto> list of Post
     * @return the list sorted
     */
    @Override
    public List<PostResponseDto> sortDesc(List<PostResponseDto> list) {
        list.sort(Comparator.comparing(this::getMilli).reversed());
        return list;
    }

    /**
     * Sort a list ascending accord of its date
     * @param list<PostResponseDto> list of Post
     * @return the list sorted
     */
    @Override
    public List<PostResponseDto> sortAsc(List<PostResponseDto> list) {
        list.sort(Comparator.comparing(this::getMilli));
        return list;
    }

    /**
     * Wrapper that select a type of sort method depending of an string  param
     * @param list<PostResponseDto> list of Post
     * @param param string that come in the request
     * @return the list sorted with the corresponding sort method
     */
    @Override
    public List<PostResponseDto> sorterWrapper(List<PostResponseDto> list, String param) {
        if (param == null) return list;
        if (param.equals("date_asc")) return sortAsc(list);
        if (param.equals("date_desc")) return sortDesc(list);
        return list;
    }

    /**
     * Get the milliseconds of the date on a PostResponseDto and check if the date is correct.
     * @param post Post object
     * @return the milliseconds from the post
     * @throws LogicValidationException if the format can't be parsed
     */
    private Long getMilli(PostResponseDto post) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return simpleFormat.parse(post.getDate()).getTime();
        } catch (ParseException ex) {
            String error = "The format from the dates is wrong, the correct format is DD-MM-YYYY";
            throw new LogicValidationException(error);
        }
    }

}
