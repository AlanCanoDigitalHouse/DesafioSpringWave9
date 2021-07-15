package com.mercadolibre.socialmeli.repositories.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.dtos.posts.requests.PostRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.requests.PromoPostRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PostResponseDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.ProductResponseDTO;
import com.mercadolibre.socialmeli.repositories.interfaces.IPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository("postsRepo")
public class PostRepositoryImpl implements IPostRepository {

    private static List<PostResponseDTO> postList;
    private static final AtomicInteger idGenerated = new AtomicInteger(0);

    public PostRepositoryImpl() {
        postList = loadDatabase();
    }

    @Override
    public PostResponseDTO addNew(PostRequestDTO post, ProductResponseDTO newProduct) {
        PostResponseDTO newPost = new PostResponseDTO(post.getUserId(),
                                                    idGenerated.incrementAndGet(),
                                                    post.getDate(),
                                                    newProduct,
                                                    post.getCategory(),
                                                    post.getPrice(),
                                                    false,
                                                    0.0);
        postList.add(newPost);
        return newPost;
    }

    @Override
    public List<PostResponseDTO> findAll() {
        return postList;
    }

    @Override
    public List<PostResponseDTO> postsOf(Integer userId) {
        return postList.stream().filter(post -> post.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public PostResponseDTO addNewPromo(PromoPostRequestDTO promoPost, ProductResponseDTO newProduct) {
        PostResponseDTO newPost = new PostResponseDTO(promoPost.getUserId(),
                idGenerated.incrementAndGet(),
                promoPost.getDate(),
                newProduct,
                promoPost.getCategory(),
                promoPost.getPrice(),
                promoPost.getHasPromo(),
                promoPost.getDiscount());
        postList.add(newPost);
        return newPost;
    }

    @Override
    public List<PostResponseDTO> promoPostsOf(Integer userId) {
        return postList.stream().filter(post -> post.getUserId().equals(userId)).filter(PostResponseDTO::getHasPromo).collect(Collectors.toList());
    }

    private List<PostResponseDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/posts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapToObject(file);
    }

    private List<PostResponseDTO> mapToObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PostResponseDTO>> typeReference = new TypeReference<>() {
        };
        List<PostResponseDTO> postsBase = null;
        try {
            postsBase = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return postsBase;
    }
}
