package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.dtos.PostDTO;
import com.mercadolibre.socialmeli.dtos.PromoPostDTO;
import com.mercadolibre.socialmeli.repositories.interfaces.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final List<PostDTO> postList = new LinkedList<>();
    private final AtomicInteger idPostCounter = new AtomicInteger(0);
    private final AtomicInteger idProductCounter = new AtomicInteger(0);

    @Override
    public void createPost(PostDTO postDTO) {
        postDTO.setId_post(idPostCounter.incrementAndGet());
        postDTO.getDetail().setProduct_id(idProductCounter.incrementAndGet());
        postList.add(postDTO);
    }

    @Override
    public List<PostDTO> getPostsBySeller(Integer sellerId) {
        return postList.stream()
                .filter(p -> p.getUserId().equals(sellerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PromoPostDTO> getPromoPostsBySeller(Integer sellerId) {
        List<PostDTO> postDTOList = getPostsBySeller(sellerId);

        return postDTOList.stream()
                .filter(p -> p instanceof PromoPostDTO)
                .map(p -> (PromoPostDTO) p)
                .collect(Collectors.toList());
    }

}
