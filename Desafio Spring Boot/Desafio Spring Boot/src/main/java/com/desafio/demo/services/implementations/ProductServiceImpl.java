package com.desafio.demo.services.implementations;

import com.desafio.demo.dtos.products.request.ProductRequestDto;
import com.desafio.demo.dtos.products.response.PostResponseDto;
import com.desafio.demo.dtos.products.response.ProductResponseDto;
import com.desafio.demo.dtos.users.FollowedResponseDto;
import com.desafio.demo.services.ProductService;
import com.desafio.demo.exceptions.InvalidDateFormatException;
import com.desafio.demo.exceptions.InvalidOrderingCriterionException;
import com.desafio.demo.repositories.ProductRepository;
import com.desafio.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private UserService userService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public void releasePost(ProductRequestDto productRequestDto) {
        validateDate(productRequestDto);
        productRepository.savePost(productRequestDto);
    }

    private void validateDate(ProductRequestDto productRequestDto) {
        try {
            Date date =new SimpleDateFormat("dd-MM-yyyy").parse(productRequestDto.getDate());
        } catch (ParseException e) {
            throw  new InvalidDateFormatException();
        }
    }

    @Override
    public ProductResponseDto getLastsPosts(int userId, String order) {
        FollowedResponseDto followed = userService.getFollowedList(userId, null);
        List<Integer> followedIds = followed.getFollowed().stream().map(followId -> followId.getUserId()).collect(Collectors.toList());
        List<PostResponseDto> posts = followedIds.stream()
                .map(aFollowedId -> productRepository.getAllPostBySellerId(aFollowedId))
                .flatMap(List::stream)
                .collect(Collectors.toList())
                .stream()
                .filter(post -> isAtMostTwoWeeksOld(post))
                .collect(Collectors.toList());

        sort(posts, order);

        return new ProductResponseDto(userId, posts);
    }

    private boolean isAtMostTwoWeeksOld(PostResponseDto post) {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date daysAgo = new Date(System.currentTimeMillis() - (14 * DAY_IN_MS));
        String sDate = post.getDate();
        Date date = null;

        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
        } catch (ParseException e) {
            throw new InvalidDateFormatException();
        }

        return date.after(daysAgo);
    }

    private void sort(List<PostResponseDto> posts, String order) {

        if ("date_desc".equals(order)) {
            Collections.sort(posts, Comparator.comparing(PostResponseDto::getDate).reversed());
        } else if ("date_asc".equals(order) || null == order) {
            Collections.sort(posts, Comparator.comparing(PostResponseDto::getDate));
        } else {
            throw new InvalidOrderingCriterionException();
        }
    }

}
