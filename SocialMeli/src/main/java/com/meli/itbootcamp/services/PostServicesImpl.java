package com.meli.itbootcamp.services;

import com.meli.itbootcamp.dto.*;
import com.meli.itbootcamp.dto.request.PostRequestDTO;
import com.meli.itbootcamp.dto.request.ProductRequestDTO;
import com.meli.itbootcamp.dto.request.PromoRequestDTO;
import com.meli.itbootcamp.exceptions.PostException;
import com.meli.itbootcamp.exceptions.UserException;
import com.meli.itbootcamp.model.*;
import com.meli.itbootcamp.repositories.PostRespository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServicesImpl implements PostServices {

    private final PostRespository postRepository;
    private final UserServices userServices;

    public PostServicesImpl(PostRespository postRepository, UserServices userServices) {
        this.postRepository = postRepository;
        this.userServices = userServices;
    }

    @Override
    public ResponseDTO createNewPost(PostRequestDTO newPostDTO) throws PostException {
        UserSeller userSeller = userServices.findUserSellerById(newPostDTO.getUserId());
        ProductRequestDTO productDTO = newPostDTO.getDetail();
        Product newProduct = new Product(productDTO.getProductName(),
                productDTO.getType(), productDTO.getBrand(),
                productDTO.getColor(), productDTO.getNotes());
        Post newPost = new Post(newPostDTO.getCategory(), newProduct, newPostDTO.getPrice());
        newPost = postRepository.addNewPost(newPost);
        if (!userSeller.addNewPost(newPost)) {
            throw new PostException(PostException.ERROR);
        }
        return new ResponseDTO(200, "Post created");

    }

    @Override
    public ListPostSellerDTO getPostSeller(Integer nonSeller, Optional<String> orderByOptional) throws UserException {
        UserNonSeller userNonSeller = userServices.findUserNonSellerById(nonSeller);
        String orderBy;
        if(orderByOptional.isPresent()){
            orderBy= orderByOptional.get();
        }else {
            orderBy ="date_des";
        }
        if (Objects.isNull(userNonSeller)) {
            throw new UserException(UserException.NOT_USER);
        }
        List<Post> postSeller = userNonSeller.latestPostFromSeller();

        if (orderBy.equalsIgnoreCase("date_asc") || orderBy.equalsIgnoreCase("date_des")) {
            if (orderBy.equalsIgnoreCase("date_des")) {
                postSeller = postSeller.stream().sorted(Comparator.comparing(Post::getDate).reversed()).collect(Collectors.toList());
            } else {
                postSeller = postSeller.stream().sorted(Comparator.comparing(Post::getDate)).collect(Collectors.toList());
            }
        }
        List<PostDTO> post = new ArrayList<>();
        postSeller.forEach(p -> post.add(new PostDTO(p)));

        return new ListPostSellerDTO(userNonSeller.getUserID(), post);
    }

    @Override
    public ResponseDTO createNewPromo(PromoRequestDTO newPromo) throws UserException {
        UserSeller userSeller = userServices.findUserSellerById(newPromo.getUserId());
        if (Objects.isNull(userSeller)) {
            throw new UserException(UserException.NOT_SELLER);
        }
        ProductRequestDTO productDTO = newPromo.getDetail();
        Product newProduct = new Product(productDTO.getProductName(),
                productDTO.getType(), productDTO.getBrand(),
                productDTO.getColor(), productDTO.getNotes());
        Post newPost = new Post(newPromo.getCategory(), newProduct, newPromo.getPrice());
        newPost = postRepository.addNewPost(newPost);
        Promo newPromoPost = new Promo(newPost, newPromo.getHasPromo(), newPromo.getDiscount());
        userSeller.addNewPromo(newPromoPost);
        return new ResponseDTO(200, "Promo created");
    }

    @Override
    public CountPromoDTO quantPromo(Integer seller) {
        UserSeller userSeller = userServices.findUserSellerById(seller);
        return new CountPromoDTO(userSeller, userSeller.countPromo());
    }

    @Override
    public ListPromoSellerDTO getPromoSeller(Integer seller, Optional<String> orderByOptional) {
        UserSeller userSeller = userServices.findUserSellerById(seller);
        String orderBy;
        if(orderByOptional.isPresent()){
            orderBy= orderByOptional.get();
        }else {
            orderBy ="name_des";
        }
        List<PromoDTO> listPromoDTO = new ArrayList<>();
        List<Promo> listPromo = new ArrayList<>();
        if (orderBy.equalsIgnoreCase("name_asc") || orderBy.equalsIgnoreCase("name_des")) {
                listPromo = userSeller.getAllPromo().stream().sorted(Comparator.comparing(promo -> promo.getPostfromPromo().getItem().getProductName())).collect(Collectors.toList());
            if (orderBy.equalsIgnoreCase("name_asc")) {
                Collections.reverse(listPromo);
            }
        }
        listPromo.forEach(promo -> {
            PostDTO postDTO = new PostDTO(promo.getPost());
            listPromoDTO.add(new PromoDTO(postDTO, promo.getHasPromo(), promo.getDiscount()));
        });
        return new ListPromoSellerDTO(userSeller.getUserID(), userSeller.getUserName(), listPromoDTO);
    }
}
