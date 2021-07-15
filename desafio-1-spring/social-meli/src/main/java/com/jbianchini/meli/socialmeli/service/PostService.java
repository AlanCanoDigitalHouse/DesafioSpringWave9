package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.PostDTO;
import com.jbianchini.meli.socialmeli.dto.PostsByFollowerDTO;
import com.jbianchini.meli.socialmeli.dto.ProductDTO;
import com.jbianchini.meli.socialmeli.dto.response.ResponseDTO;
import com.jbianchini.meli.socialmeli.dto.response.SuccessResponseDTO;
import com.jbianchini.meli.socialmeli.model.Post;
import com.jbianchini.meli.socialmeli.model.Product;
import com.jbianchini.meli.socialmeli.model.User;
import com.jbianchini.meli.socialmeli.repository.IPostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PostService implements IPostService {

    private IPostRepository postRepository;
    private IUserService userService;

    public PostService(IPostRepository postRepository, IUserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public ResponseDTO newPost(PostDTO postDTO) {
        //TODO: See what kind of exception will throw this if fails.
        User user = this.userService.findByUserId(postDTO.getUserId());

        Product product = new Product(postDTO.getDetail().getProductName(), postDTO.getDetail().getType(),
                postDTO.getDetail().getBrand(), postDTO.getDetail().getColor(), postDTO.getDetail().getNotes());

        Post post = new Post(user, postDTO.getDate(), product, postDTO.getCategory(), postDTO.getPrice());

        this.postRepository.save(post);

        return new SuccessResponseDTO("Post successfully created", postDTO);
    }

    @Override
    public PostsByFollowerDTO getFollowedPostsByUserId(Integer userId, String order) {
        //get the user by its id
        User user = this.userService.findByUserId(userId);
        //get the user followed users
        List<User> followed = user.getFollowed();

        List<Post> followedPosts = new ArrayList<>();

        //add all the followed users posts to a single list
        followed.forEach(f -> followedPosts.addAll(this.postRepository.findByUserSinceTwoWeeksAgo(f)));
        //convert it to a dto list
        List<PostDTO> followedPostsDTO = this.getPostDTOList(followedPosts);
        //sort it
        this.sortByDate(followedPostsDTO, order);

        return new PostsByFollowerDTO(userId, followedPostsDTO);

    }

    private List<PostDTO> getPostDTOList(List<Post> posts) {
        List<PostDTO> postDTOS = new ArrayList<>();

        posts.forEach(p -> postDTOS.add(this.convertToPostDTO(p)));

        return postDTOS;
    }

    private void sortByDate(List<PostDTO> posts, String order) {
        switch (order) {
            case "date_asc":
                Collections.sort(posts, Comparator.comparing(PostDTO::getDate));
                break;
            case "date_desc":
            case "":
                Collections.sort(posts, Comparator.comparing(PostDTO::getDate).reversed());
                break;
        }
    }

    private PostDTO convertToPostDTO(Post p) {
        Product product = p.getProduct();
        ProductDTO productDTO =
                new ProductDTO(product.getProductName(), product.getType(), product.getBrand(), product.getColor(),
                        product.getNotes());
        return new PostDTO(p.getUser().getUserId(), p.getDate(), productDTO, p.getCategory(), p.getPrice());
    }


}
