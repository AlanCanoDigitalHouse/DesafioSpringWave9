package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.PostDTO;
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

        List<PostDTO> listPostUser = listPostTemp.stream()
                .filter(post -> post.getUserId().equals(userId) && post.getDate().after(dayToWeeksAgo))
                .collect(Collectors.toList());
        listPostUser.sort((d1, d2) -> d2.getDate().compareTo(d1.getDate()));

        UserPostsResposeDTO userPostsResposeDTO = new UserPostsResposeDTO(userId);
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        List<PostResponseDTO> listPostResponse = new ArrayList<>();
        for(PostDTO postDTO : listPostUser){
            postResponseDTO.setId_post(postDTO.getId_post());
            postResponseDTO.setDate(postDTO.getDate());
            postResponseDTO.setDetail(postDTO.getDetail());
            postResponseDTO.setCategory(postDTO.getCategory());
            postResponseDTO.setPrice(postDTO.getPrice());
            listPostResponse.add(postResponseDTO);
        }
        userPostsResposeDTO.setPosts(listPostResponse);
        return userPostsResposeDTO;
    }

    @Override
    public List<PostDTO> show() {
        return postDatabase;
    }
}
