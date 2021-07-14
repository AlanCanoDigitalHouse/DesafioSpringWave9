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

    public void createNewPost(PostRequestDto postResponseDto) {
        var productRequest = postResponseDto.getDetail();
        var product = new ProductResponseDto(productRequest);
        var post = new PostResponseDto(postResponseDto.getUserId(),
                postResponseDto.getDate(), postResponseDto.getCategory(), postResponseDto.getPrice(), product);
        productRepository.create(post);
    }

    public void createNewPromoPost(PostPromoRequestDto promoRequestDto){
        var productRequest = promoRequestDto.getDetail();
        var product = new ProductResponseDto(productRequest);
        var post = new PostResponseDto(promoRequestDto.getUserId(),
                promoRequestDto.getDate(), promoRequestDto.getCategory(), promoRequestDto.getPrice(), product);
        post.setHasPromo(promoRequestDto.getHasPromo());
        post.setDiscount(promoRequestDto.getDiscount());
        productRepository.create(post);
    }

    public List<PostResponseDto> getPosts(List<UserResponseDto> listFollowed) {
        List<PostResponseDto> posts = new ArrayList<>();
        for (UserResponseDto user : listFollowed) {
            var sellerId = user.getId();
            var postFromSeller = productRepository.getPosts().stream()
                    .filter(postResponseDto -> postResponseDto.getUserId() == sellerId).collect(Collectors.toList());
            posts.addAll(postFromSeller);
        }
        return posts;
    }

    public PostResponseDto getPost(Integer userId) {
        var post = productRepository.getPosts().stream()
                .filter(postResponseDto -> postResponseDto.getUserId() == userId).findFirst();
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new LogicValidationException("This user is not a seller or may not have any post");
        }
    }

    public List<PostResponseDto> getPromoPost(Integer userId){
        return productRepository.getPosts().stream()
                .filter(postResponseDto -> postResponseDto.getUserId() == userId && postResponseDto.getHasPromo())
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> sortDesc(List<PostResponseDto> list) {
        list.sort(Comparator.comparing(this::getMilli).reversed());
        return list;
    }

    @Override
    public List<PostResponseDto> sortAsc(List<PostResponseDto> list) {
        list.sort(Comparator.comparing(this::getMilli));
        return list;
    }

    @Override
    public List<PostResponseDto> sorterWrapper(List<PostResponseDto> list, String param) {
        if (param == null) return list;
        if (param.equals("date_asc")) return sortAsc(list);
        if (param.equals("date_desc")) return sortDesc(list);
        return list;
    }

    private Long getMilli(PostResponseDto post) {
        System.out.println(post.getDate());
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return simpleFormat.parse(post.getDate()).getTime();
        } catch (ParseException ex) {
            throw new LogicValidationException("The format from the dates is wrong, the correct format is DD-MM-YYYY");
        }
    }

}
