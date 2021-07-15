package com.desafio.spring.repositories;

import com.desafio.spring.dtos.PostDto;
import com.desafio.spring.dtos.ProductDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRespository{
    private List<PostDto> posts = postList();

    private List<PostDto> postList()
    {
        List<PostDto> posts = new ArrayList<>();
        posts.add(new PostDto(1235, "2020-07-29", new ProductDto("telefono a20","movil", "samsung", "blue/black", "Free companies" ), 3, 300.00));
        posts.add(new PostDto(1235, "2020-06-29", new ProductDto("telefono g8","movil", "motorola", "blue/black", "Free companies" ), 3, 400.00));
        return posts;
    }
    @Override
    public void newPost(PostDto post) {
        posts.add(post);
    }

    public static List<LocalDate> compareTo(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate = LocalDate.parse(date, formatter);
        LocalDate endDate = LocalDate.now();
        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsLasted(Integer userId, String date) {
        posts.sort((d1,d2) -> LocalDate.parse(d1.getDate()).compareTo(LocalDate.parse(d2.getDate())));


        return posts;
    }

}
