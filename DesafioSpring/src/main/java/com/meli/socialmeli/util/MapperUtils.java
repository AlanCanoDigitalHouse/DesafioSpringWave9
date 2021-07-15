package com.meli.socialmeli.util;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.dto.ProductDTO;
import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.dto.request.NewPostRequest;
import com.meli.socialmeli.dto.request.NewPromoPostRequest;
import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.model.Product;
import com.meli.socialmeli.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

  public static List<UserDTO> usersToDTOs(Collection<User> users) {
    return users.stream().map(MapperUtils::userToDto).collect(Collectors.toCollection(ArrayList::new));
  }

  public static Post dtoToPost(Integer userId, PostDTO postDTO) {
    return Post.builder()
            .userId(userId)
            .postId(postDTO.getId_post())
            .date(postDTO.getDate())
            .detail(dtoToProdcut(postDTO.getDetail()))
            .category(postDTO.getCategory())
            .price(postDTO.getPrice())
            .hasPromo(postDTO.getHasPromo())
            .discount(postDTO.getDiscount())
            .build();
  }

  public static PostDTO toPostDTO(Post post) {
    return PostDTO.builder()
            .userId(post.getUserId())
            .id_post(post.getPostId())
            .date(post.getDate())
            .detail(toProdcutDTO(post.getDetail()))
            .category(post.getCategory())
            .price(post.getPrice())
            .hasPromo(post.getHasPromo())
            .discount(post.getDiscount())
            .build();
  }

  public static ProductDTO toProdcutDTO(Product product) {
    return ProductDTO.builder()
            .productName(product.getProductName())
            .type(product.getType())
            .brand(product.getBrand())
            .color(product.getColor())
            .notes(product.getNotes())
            .build();
  }

  public static Product dtoToProdcut(ProductDTO productDTO) {
    return Product.builder()
            .productId(productDTO.getProduct_id())
            .productName(productDTO.getProductName())
            .type(productDTO.getType())
            .brand(productDTO.getBrand())
            .color(productDTO.getColor())
            .notes(productDTO.getNotes())
            .build();
  }

  public static PostDTO buildDTOFromRequest(NewPostRequest request) {
    return PostDTO.builder()
            .userId(request.getUserId())
            .id_post(request.getId_post())
            .date(request.getDate())
            .detail(request.getDetail())
            .category(request.getCategory())
            .price(request.getPrice())
            .hasPromo(request.getHasPromo())
            .discount(request.getDiscount())
            .build();
  }

  public static PostDTO buildDTOFromRequest(NewPromoPostRequest request) {
    return PostDTO.builder()
            .id_post(request.getId_post())
            .userId(request.getUserId())
            .date(request.getDate())
            .detail(request.getDetail())
            .category(request.getCategory())
            .price(request.getPrice())
            .hasPromo(request.getHasPromo())
            .discount(request.getDiscount())
            .build();
  }

  public static List<PostDTO> postToDTOList(List<Post> posts) {
    ArrayList<PostDTO> postDTOs = new ArrayList<>();
    posts.forEach(post -> postDTOs.add(toPostDTO(post)));
    return postDTOs;
  }

  public static UserDTO userToDto(User user) {
    return new UserDTO(user.getUserId(), user.getUserName());
  }
}
