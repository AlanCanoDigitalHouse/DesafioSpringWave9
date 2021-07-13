package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.dtos.PostDTO;
import com.mercadolibre.socialmeli.repositories.interfaces.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<PostDTO> postList = new LinkedList<>();
    private AtomicInteger idPostCounter = new AtomicInteger(0);
    private AtomicInteger idProductCounter= new AtomicInteger(0);

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
}
