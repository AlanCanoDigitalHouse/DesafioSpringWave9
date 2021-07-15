package com.mercado_libre.bootcamp.spring.desafio.services.post;

import com.mercado_libre.bootcamp.spring.desafio.dtos.request.NewProductRequestDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.request.NewPromoRequestDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.PromoCountResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.PromoListResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.models.Post;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import com.mercado_libre.bootcamp.spring.desafio.services.seller.SellerServiceImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.strategies.SortPostStrategy;
import com.mercado_libre.bootcamp.spring.desafio.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final SellerServiceImpl sellerService;

    private final SortPostStrategy sortPostStrategy;

    @Override
    public HttpStatus addNewProduct(NewProductRequestDTO newProductRequest) {

        Seller seller = sellerService.getSeller(newProductRequest.getUserId());

        Post post = Post.builder()
                .date(DateUtils.convertToLocalDateViaInstant(newProductRequest.getDate()))
                .category(newProductRequest.getCategory())
                .detail(newProductRequest.getDetail())
                .price(newProductRequest.getPrice())
                .build();

        sellerService.savePost(seller.getUserId(), post);

        return HttpStatus.OK;
    }

    @Override
    public HttpStatus addNewPromoProduct(NewPromoRequestDTO newPromoRequest) {

        Seller seller = sellerService.getSeller(newPromoRequest.getUserId());

        Post post = Post.builder()
                .date(DateUtils.convertToLocalDateViaInstant(newPromoRequest.getDate()))
                .category(newPromoRequest.getCategory())
                .detail(newPromoRequest.getDetail())
                .price(newPromoRequest.getPrice())
                .hasPromo(newPromoRequest.getHasPromo())
                .discount(newPromoRequest.getDiscount())
                .build();

        sellerService.savePost(seller.getUserId(), post);

        return HttpStatus.OK;
    }

    @Override
    public PromoCountResponseDTO getPromosCount(int sellerId) {

        Seller seller = sellerService.getSeller(sellerId);

        int count = (int) seller.getPosts().stream()
                .filter(Post::isHasPromo)
                .count();

        return new PromoCountResponseDTO(seller.getUserId(), seller.getUserName(), count);
    }

    @Override
    public PromoListResponseDTO getPromoList(int sellerId, String order) {
        Seller seller = sellerService.getSeller(sellerId);

        List<Post> posts = seller.getPosts().stream().filter(Post::isHasPromo)
                .collect(Collectors.toList());

        sortPostStrategy.getImplementation(order).sort(posts);

        return new PromoListResponseDTO(seller.getUserId(), seller.getUserName(), posts);
    }
}
