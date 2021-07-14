package com.example.socialmeli.services.implementations;

import com.example.socialmeli.domains.Post;
import com.example.socialmeli.domains.PostPromo;
import com.example.socialmeli.domains.User;
import com.example.socialmeli.dtos.request.PostPromoRequestDTO;
import com.example.socialmeli.dtos.request.PostRequestDTO;
import com.example.socialmeli.dtos.response.*;
import com.example.socialmeli.exceptions.DataNotFound;
import com.example.socialmeli.exceptions.DateParserException;
import com.example.socialmeli.exceptions.OrderInvalidFormatException;
import com.example.socialmeli.handlers.DateUtils;
import com.example.socialmeli.handlers.ObjectMapper;
import com.example.socialmeli.repositories.interfaces.PostRepository;
import com.example.socialmeli.services.interfaces.PostServices;
import com.example.socialmeli.services.interfaces.UserServices;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServicesImpl implements PostServices {

    private final PostRepository postRepository;
    private final UserServices userServices;

    public PostServicesImpl(PostRepository postRepository, UserServices userServices) {
        this.postRepository = postRepository;
        this.userServices = userServices;
    }

    @Override
    public SuccessResponseDTO newPost(PostRequestDTO postRequestDTO) throws DataNotFound, DateParserException {
        User user = userServices.findByUserId(postRequestDTO.getUserId());
        try {
            Post post = ObjectMapper.toPostDTO(postRequestDTO, user);
            postRepository.savePost(post);
            user.getPosts().add(post);
        } catch (ParseException e) {
            throw new DateParserException();
        }
        return new SuccessResponseDTO(200, "Post created");
    }


    @Override
    public List<PostResponseDTO> getPostByUserId(Integer userId) throws DataNotFound {
        List<Post> postFollowed = findPostUser(userId);
        Date date = DateUtils.getDateTwoWeekBefore();
        postFollowed = postFollowed.stream().filter(p -> p.getDate().after(date)).collect(Collectors.toList());
        return ObjectMapper.toPostResponseOrderNewFirst(postFollowed);

    }

    @Override
    public List<PostResponseDTO> getPostByUserId(Integer userId, String order) throws DataNotFound, OrderInvalidFormatException {
        if (order.equalsIgnoreCase("date_asc") || order.equalsIgnoreCase("date_desc")) {
            List<PostResponseDTO> postOrder;
            if (order.equalsIgnoreCase("date_desc")) {
                postOrder = ObjectMapper.toPostResponseOrderNewFirst(findPostUser(userId));
            } else {
                postOrder = ObjectMapper.toPostResponseOrderLastFirst(findPostUser(userId));
            }
            return postOrder;
        }
        throw new OrderInvalidFormatException(OrderInvalidFormatException.ERROR_DATE);
    }

    @Override
    public SuccessResponseDTO newPromoPost(PostPromoRequestDTO postRequestDTO) throws DataNotFound, DateParserException {
        User user = userServices.findByUserId(postRequestDTO.getUserId());
        try {
            PostPromo post = ObjectMapper.toPostPromoDTO(postRequestDTO, user);
            postRepository.savePost(post);
            user.getPostsPromo().add(post);
        } catch (ParseException e) {
            throw new DateParserException();
        }
        return new SuccessResponseDTO(200, "Post created");
    }

    @Override
    public PostPromoResponseDTO getPostPromoByUser(Integer userId) throws DataNotFound {
        User user = userServices.findByUserId(userId);
        return new PostPromoResponseDTO(user.getUserId(), user.getUserName(), user.getPostsPromo().size());
    }

    @Override
    public List<PostResponseDTO> getPostPromoDetailByUser(Integer userId) throws DataNotFound {
        User user = userServices.findByUserId(userId);
        return ObjectMapper.toPostResponse(user.getPostsPromo());
    }

    private List<Post> findPostUser(Integer userId) throws DataNotFound {
        User user = userServices.findByUserId(userId);
        List<Post> postFollowed = new ArrayList<>();
        user.getFollowed().forEach(p -> postFollowed.addAll(p.getPosts()));
        return postFollowed;
    }

}
