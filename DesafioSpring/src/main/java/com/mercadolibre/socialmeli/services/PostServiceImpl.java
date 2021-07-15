package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.request.PostRequestDTO;
import com.mercadolibre.socialmeli.dtos.response.PostResponseDTO;
import com.mercadolibre.socialmeli.dtos.response.UserFollowedLatestPostsResponseDTO;
import com.mercadolibre.socialmeli.models.Post;
import com.mercadolibre.socialmeli.models.User;
import com.mercadolibre.socialmeli.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }


    @Override
    public void addPost(PostRequestDTO postRequestDTO) {
        if (this.userService.userExist(postRequestDTO.getUserId())) {
            this.postRepository.addPost(postRequestDTO);
        }

    }


    @Override
    public UserFollowedLatestPostsResponseDTO followedLatestPosts(Integer userId, String order) {

        UserFollowedLatestPostsResponseDTO responseDTO = new UserFollowedLatestPostsResponseDTO();

        List<User> followedUserList = this.userService.findUserByUserId(userId).get().getFollowed();
        List<Post> followedPostsList = new ArrayList<>();
        List<PostResponseDTO> followedPostsListDTO = new ArrayList<>();

        followedUserList.forEach(u -> followedPostsList.addAll(this.postRepository.findPostsByUserId(u.getUserID())));

        followedPostsList.forEach(post -> followedPostsListDTO.add(new PostResponseDTO(post)));

        if (order.equals("date_desc")) {
            Collections.sort(followedPostsListDTO, Collections.reverseOrder());
        } else if (order.equals("date_asc")) {
            Collections.sort(followedPostsListDTO);
        }

        responseDTO.setUserId(userId);
        responseDTO.setPosts(followedPostsListDTO.stream().filter(post -> post.getDate().isAfter(LocalDate.now().minus(Period.ofDays(14)))).collect(Collectors.toList()));
        return responseDTO;

    }

}

