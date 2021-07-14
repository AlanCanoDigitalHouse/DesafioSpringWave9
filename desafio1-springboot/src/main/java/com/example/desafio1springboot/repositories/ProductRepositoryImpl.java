package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.PostDTO;
import com.example.desafio1springboot.dtos.PostInPromoDTO;
import com.example.desafio1springboot.dtos.responseDTO.PostResponseDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserPostsResposeDTO;
import com.example.desafio1springboot.exceptions.PostNotValidDateException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        Date dayToWeeksAgo = new Date(new Date().getTime() - twoWeeks);

        var listPostFiltered = listPostTemp.stream()
                .filter(post -> post.getUserId().equals(userId) && post.getDate().after(dayToWeeksAgo))
                .collect(Collectors.toList());
        List<PostResponseDTO> postsResponse = new ArrayList<>();
        listPostFiltered.stream().forEach(postDTO -> postsResponse.add(new PostResponseDTO(postDTO.getId_post(), postDTO.getDate(), postDTO.getDetail(), postDTO.getCategory(), postDTO.getPrice())));

        UserPostsResposeDTO userPostsResposeDTO = new UserPostsResposeDTO(userId);
        userPostsResposeDTO.setPosts(postsResponse);
        userPostsResposeDTO.getPosts().sort((d1, d2) -> d2.getDate().compareTo(d1.getDate()));
        return userPostsResposeDTO;
    }

    @Override
    public List<PostDTO> show() {
        return postDatabase;
    }

    @Override
    public void addNewPromoPost(PostInPromoDTO post) throws PostNotValidDateException {
        if(post.getDate().after(new Date()))
            throw new PostNotValidDateException();
        postDatabase.add(post);
    }
}
