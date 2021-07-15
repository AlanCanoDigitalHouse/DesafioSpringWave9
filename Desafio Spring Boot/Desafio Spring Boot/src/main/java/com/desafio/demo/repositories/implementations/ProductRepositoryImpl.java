package com.desafio.demo.repositories.implementations;

import com.desafio.demo.dtos.products.request.DetailDto;
import com.desafio.demo.dtos.products.request.ProductRequestDto;
import com.desafio.demo.dtos.products.response.PostResponseDto;
import com.desafio.demo.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private ArrayList<ProductRequestDto> posts;


    @Override
    public void initialize() {
        posts=new ArrayList<>();

        posts.add(new  ProductRequestDto(1, "13-07-2021", new DetailDto("lampara",
                "zen", "lumens",  "negra",""), 7, 1500.00));

        posts.add(new  ProductRequestDto(1, "14-07-2021", new DetailDto("mesa",
                "de comedor", "generica",  "marron",""), 5, 30000.00));

        posts.add(new  ProductRequestDto(1, "08-07-2021", new DetailDto("microondas",
                "n/c", "liliana",  "blanco",""), 13, 60000.00));
        posts.add(new  ProductRequestDto(1, "10-01-2021", new DetailDto("estufa",
                "electrica", "turbo",  "negra",""), 18, 800.00));

        posts.add(new  ProductRequestDto(2, "14-07-2021", new DetailDto("lampara",
                "zen", "lumens",  "negra",""), 7, 1500.00));

        posts.add(new  ProductRequestDto(2, "10-07-2021", new DetailDto("mesa",
                "de comedor", "generica",  "marron",""), 5, 30000.00));

        posts.add(new  ProductRequestDto(2, "05-07-2021", new DetailDto("microondas",
                "n/c", "liliana",  "blanco",""), 13, 60000.00));
        posts.add(new  ProductRequestDto(2, "01-07-2021", new DetailDto("estufa",
                "electrica", "turbo",  "negra",""), 18, 800.00));


    }

    @Override
    public void savePost(ProductRequestDto productRequestDto) {
        posts.add(productRequestDto);
    }

    @Override
    public List<PostResponseDto> getAllPostBySellerId(Integer aFollowedId) {
        return posts.stream()
                .filter(entry -> entry.getUserId()==aFollowedId)
                .map(entry -> new PostResponseDto(
                        entry.getDate(),
                        entry.getDetail(),
                        entry.getCategory(),
                        entry.getPrice()))
                .collect(Collectors.toList());
    }
    }

