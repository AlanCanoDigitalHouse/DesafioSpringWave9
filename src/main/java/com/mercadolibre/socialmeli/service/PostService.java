package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.PostDto;
import com.mercadolibre.socialmeli.dto.ProductDto;
import com.mercadolibre.socialmeli.dto.request.NewPostRequest;
import com.mercadolibre.socialmeli.dto.response.PostResponse;
import com.mercadolibre.socialmeli.dto.response.UserIdPostResponse;
import com.mercadolibre.socialmeli.entity.Post;
import com.mercadolibre.socialmeli.entity.Product;
import com.mercadolibre.socialmeli.logic.PostUtils;
import com.mercadolibre.socialmeli.repository.IPostRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    private final IProductService productService;
    private final IPostRepository postRepository;
    private final IFollowService followService;
    private final ConversionService conversionService;
    private final PostUtils postUtils;

    public PostService(IProductService productService, IPostRepository postRepository, IFollowService followService, ConversionService conversionService, PostUtils postUtils) {
        this.productService = productService;
        this.postRepository = postRepository;
        this.followService = followService;
        this.conversionService = conversionService;
        this.postUtils = postUtils;
    }

    @Override
    public ResponseEntity createPost(NewPostRequest post) {
        Product p = productService.createProduct(post);
        postRepository.save(new Post(conversionService.convert(post, PostDto.class), p.getProductId()));
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Message", "Status Code 200 "));
    }

    @Override
    public UserIdPostResponse listProductsFollowed(Integer id, String order) throws ParseException {
        List<Integer> listFollowed;
        List<Post> listPost;
        if (order != null) {
            if (order.equals("date_asc")) {
                listFollowed = followService.listUserFollowed(id);
                listPost = postRepository.findByUserIdASC(listFollowed, postUtils.dateMinius15(), postUtils.dateNow());
            } else if (order.equals("date_desc")) {
                listFollowed = followService.listUserFollowed(id);
                listPost = postRepository.findByUserIdDESC(listFollowed, postUtils.dateMinius15(), postUtils.dateNow());
            } else {
                listFollowed = followService.listUserFollowed(id);
                listPost = postRepository.findByUserIdIn(listFollowed, postUtils.dateMinius15(), postUtils.dateNow());
            }
        } else {
            listFollowed = followService.listUserFollowed(id);
            listPost = postRepository.findByUserIdIn(listFollowed, postUtils.dateMinius15(), postUtils.dateNow());
        }

        List<Product> listProducts = listPost.stream().map((p) -> productService.listProductsFollowed(p.getProductId())).collect(Collectors.toList());
        List<PostResponse> listPostRequest = new ArrayList<>();

        listPost.forEach(post -> {
            Product product = listProducts.stream().filter(produt -> produt.getProductId().equals(post.getProductId())).findFirst().orElse(null);
            ProductDto p = ProductDto.builder().productName(product.getProductName())
                    .productNotes(product.getProductNotes())
                    .productBrand(product.getProductBrand())
                    .productColor(product.getProductColor())
                    .productType(product.getProductType())
                    .build();
            System.out.println(p);
            listPostRequest.add(new PostResponse(post.getPostId(), post.getDate(), p, post.getCategory(), post.getPrice()));
        });
        return new UserIdPostResponse(id, listPostRequest);
    }
}
