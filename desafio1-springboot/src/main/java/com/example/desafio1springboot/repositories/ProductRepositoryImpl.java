package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.*;
import com.example.desafio1springboot.exceptions.PostNotValidDateException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements IProductRepository{
    List<PostDTO> postDatabase = new ArrayList<>();

    @Override
    public void addNewPost(PostDTO post) throws PostNotValidDateException {
        if(post.getDate().after(new Date()))
            throw new PostNotValidDateException();
        postDatabase.add(post);
    }

    @Override
    public UserPostsResposeDTO listsPostsFromUser_(Integer userId) {
        List<PostDTO> listPostTemp = postDatabase;
        long twoWeeks = 604800000 * 2;
        // Instant zonedTime = ZonedDateTime.now().minusWeeks(2).toInstant();
        Date dayToWeeksAgo = new Date(new Date().getTime() - twoWeeks);

        var listPostFiltered = listPostTemp.stream()
                .filter(post -> post.getUserId().equals(userId) && post.getDate().after(dayToWeeksAgo))
                .collect(Collectors.toList());
        List<PostResponseDTO> postsResponse = new ArrayList<>();
        listPostFiltered.stream().forEach(postDTO -> postsResponse.add(new PostResponseDTO(postDTO.getId_post(), postDTO.getDate(), postDTO.getDetail(), postDTO.getCategory(), postDTO.getPrice())));

        UserPostsResposeDTO<PostResponseDTO> userPostsResposeDTO = new UserPostsResposeDTO(userId);
        userPostsResposeDTO.setPosts(postsResponse);
        userPostsResposeDTO.getPosts().sort((d1, d2) -> d2.getDate().compareTo(d1.getDate()));
        // Todo: (FeedBack compañero)
        // userPostsResposeDTO.getPosts().sort(Comparator.comparing(PostResponseDTO::getDate)); -> ASC
        // userPostsResposeDTO.getPosts().sort(Comparator.comparing(PostResponseDTO::getDate).reversed()); -> DESC
        // userPostsResposeDTO.getPosts().sort(Comparator.comparing(PostResponseDTO::getDate).thenComparing()); -> Multi sort
        return userPostsResposeDTO;
    }

    @Override
    public void addNewPromoPost(PostInPromoDTO post) throws PostNotValidDateException {
        if(post.getDate().after(new Date()))
            throw new PostNotValidDateException();
        postDatabase.add(post);
    }

    @Override
    public UserSellerResponseDTO postPromoMeBy_(Integer userId, String userName) {
        UserSellerResponseDTO userSellerResponseDTO = new UserSellerResponseDTO(userId, userName);
        userSellerResponseDTO.setPromoproducts_count(getPromoPostByUser_(userId).size());
        return userSellerResponseDTO;
    }

    public List<PostInPromoDTO> getPromoPostByUser_(Integer userId) {
        var userPosts = postDatabase.stream().filter(postDTO -> postDTO.getUserId().equals(userId)).collect(Collectors.toList());
        List<PostInPromoDTO> listPromoByUser = new ArrayList<>();
        for(PostDTO post : userPosts) {
            if(post instanceof PostInPromoDTO) {
                listPromoByUser.add((PostInPromoDTO) post);
            }
        }
        return listPromoByUser;
    }
}
