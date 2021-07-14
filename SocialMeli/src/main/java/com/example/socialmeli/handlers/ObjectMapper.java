package com.example.socialmeli.handlers;

import com.example.socialmeli.domains.Post;
import com.example.socialmeli.domains.PostPromo;
import com.example.socialmeli.domains.Product;
import com.example.socialmeli.domains.User;
import com.example.socialmeli.dtos.request.PostPromoRequestDTO;
import com.example.socialmeli.dtos.request.PostRequestDTO;
import com.example.socialmeli.dtos.response.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    public static List<PostResponseDTO> toPostResponseOrderNewFirst(List<Post> post) {
        List<Post> sorted = post.stream().sorted(Comparator.comparing(Post::getDate).reversed())
                .collect(Collectors.toList());
        return toPostResponse(sorted);
    }

    public static List<PostResponseDTO> toPostResponseOrderLastFirst(List<Post> post) {
        List<Post> sorted = post.stream().sorted(Comparator.comparing(Post::getDate))
                .collect(Collectors.toList());
        return toPostResponse(sorted);
    }

    public static List<PostResponseDTO> toPostResponse(List<Post> order) {
        List<PostResponseDTO> postResponseDTOS = new ArrayList<>();
        for (Post post : order) {
            PostResponseDTO p = new PostResponseDTO();
            p.setUserId(post.getUser().getUserId());
            if (post instanceof PostPromo) {
                p.getPosts().add(new PostPromoDetailResponseDTO((PostPromo) post));
            } else {
                p.getPosts().add(new PostDetailResponseDTO(post));
            }
            postResponseDTOS.add(p);
        }
        return postResponseDTOS;
    }

    public static Post toPostDTO(PostRequestDTO dto, User user) throws ParseException {
        return new Post(user, DateUtils.stringToDate(dto.getDate()),
                new Product(dto.getDetail()), String.valueOf(dto.getCategory()), dto.getPrice());
    }

    public static PostPromo toPostPromoDTO(PostPromoRequestDTO dto, User user) throws ParseException {
        return new PostPromo(user, DateUtils.stringToDate(dto.getDate()),
                new Product(dto.getDetail()), String.valueOf(dto.getCategory()),
                dto.getPrice(), dto.getHasPromo(), dto.getDiscount());
    }

    public static UserFollowedDetailsDTO toUserFollowed(User user) {
        UserFollowedDetailsDTO userFollowed = new UserFollowedDetailsDTO();
        userFollowed.setUserId(user.getUserId());
        userFollowed.setUserName(user.getUserName());
        List<UserResponseDTO> usersFollowed = new ArrayList<>();
        user.getFollowed().forEach(fr -> usersFollowed.add(new UserResponseDTO(fr.getUserId(), fr.getUserName())));
        userFollowed.setFollowed(usersFollowed);
        return userFollowed;
    }

    public static UserFollowersDetailsDTO toUserFollowersDetail(User user) {
        UserFollowersDetailsDTO userFollowers = new UserFollowersDetailsDTO();
        userFollowers.setUserId(user.getUserId());
        userFollowers.setUserName(user.getUserName());
        List<UserResponseDTO> userFollower = new ArrayList<>();
        user.getFollowers().forEach(fr -> userFollower.add(new UserResponseDTO(fr.getUserId(), fr.getUserName())));
        userFollowers.setFollowers(userFollower);
        return userFollowers;
    }

    public static UserFollowersDTO toUserFollowersDTO(User user) {
        UserFollowersDTO userFollowersDTO = new UserFollowersDTO();
        userFollowersDTO.setUserId(user.getUserId());
        userFollowersDTO.setUserName(user.getUserName());
        userFollowersDTO.setFollowers_count(user.getFollowers().size());
        return userFollowersDTO;
    }
}
