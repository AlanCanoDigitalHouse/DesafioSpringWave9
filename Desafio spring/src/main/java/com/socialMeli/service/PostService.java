package com.socialMeli.service;

import com.socialMeli.dto.request.product.PostInfoToCreateDTO;
import com.socialMeli.dto.response.PostInfoResponseDTO;
import com.socialMeli.dto.response.ProductDetailResponseDTO;
import com.socialMeli.dto.response.ProductsSellersThatUserFollowsDTO;
import com.socialMeli.exception.exception.DateNotValidException;
import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.PostBuilder;
import com.socialMeli.model.PostModel;
import com.socialMeli.model.UserModel;
import com.socialMeli.repository.PostRepository;
import com.socialMeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void addNewPost(@Valid PostInfoToCreateDTO dataPost) throws ParseException, ModelNotExists, ModelAlreadyExists, DateNotValidException {
        UserModel userModel = userRepository.findById(dataPost.getUserId());
        PostModel postModel = new PostBuilder(postRepository.getNextId())
                .setBrand(dataPost.getDetail().getBrand())
                .setCategory(dataPost.getCategory())
                .setColor(dataPost.getDetail().getColor())
                .setDate(dataPost.getDate())
                .setNotes(dataPost.getDetail().getNotes())
                .setPrice(dataPost.getPrice())
                .setProduct_id(postRepository.getNextId())
                .setType(dataPost.getDetail().getType())
                .setProductName(dataPost.getDetail().getProductName())
                .setUserId(userModel.getId())
                .build();

        postRepository.insert(postModel);
    }

    public ProductsSellersThatUserFollowsDTO postSellersThatUserFollows(int userId) throws ModelNotExists {
        UserModel user = userRepository.findById(userId);
        List<UserModel> usersFollowed = getListUserById(user.getFollowed());
        List<PostModel> posts = getPostsOfUsersBeforeADate(usersFollowed, restOneWeekToDate(new Date()));

        List<PostInfoResponseDTO> postInfoResponseDTOS = posts.stream().map(postModel ->{
            //Create detail
            ProductDetailResponseDTO info = new ProductDetailResponseDTO(postModel.getProduct_id(),postModel.getProductName(),postModel.getType(), postModel.getBrand(), postModel.getColor(), postModel.getNotes());
            //Create PostInfo
            return new PostInfoResponseDTO(postModel.getId(), postModel.getDate(), info, String.valueOf(postModel.getCategory()), postModel.getPrice());
        }).collect(Collectors.toList());

        return new ProductsSellersThatUserFollowsDTO(user.getId(),postInfoResponseDTOS);
    }

    private List<UserModel> getListUserById(List<Integer> ids) throws ModelNotExists {
       List<UserModel> users = new ArrayList<>();
       for(Integer id:ids) users.add(userRepository.findById(id));
       return users;
    }
    private List<PostModel> getPostsOfUsersBeforeADate(List<UserModel> users, Date limitDateIncluded){
        List<PostModel> posts = new ArrayList<>();
        for(UserModel user: users){
            posts.addAll(getPostsOfUserBeforeADate(user, limitDateIncluded));
        }
        posts.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return posts;
    }
    private List<PostModel> getPostsOfUserBeforeADate(UserModel user, Date limitDateIncluded){
        List<PostModel> all = postRepository.findAll();
        return all.stream().filter(post -> post.getUserId() == user.getId() && dateIsInLimit(limitDateIncluded, post.getDate())).collect(Collectors.toList());
    }
    private boolean dateIsInLimit(Date limit, Date eval){
        Date today = new Date();
        return eval.before(today) && eval.after(limit);
    }
    private Date restOneWeekToDate(Date date){
        //Parsing to localDate, more easy https://stackoverflow.com/questions/11882926/how-to-subtract-x-day-from-a-date-object-in-java
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        ldt = ldt.minusDays(7);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
