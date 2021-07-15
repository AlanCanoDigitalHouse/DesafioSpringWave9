package com.example.desafiospring.repositories;


import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;
import com.example.desafiospring.services.ISellerService;
import com.example.desafiospring.utilities.FakeBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostRepositoryImpl implements IPostRepository{

    @Autowired
    private FakeBD fakeBD;

    @Autowired
    private ISellerService iSellerService;

    public PostDTO findPostById(Integer postId) {
        Optional<PostDTO> item = fakeBD.getPostDTOList().stream()
                .filter(postDTO -> postDTO.getUserID().equals(postId)).findFirst();
        return item.orElse(null);
    }

    public void newPost(PostDTO postDTO) throws UserNotFoundByIdException {
        List<PostDTO> posts = this.iSellerService.findSellerDTOById(postDTO.getUserID()).getPostDTOList();
        if(posts !=null){
            if(posts.size() >= 1){
                postDTO.setPostID(posts.size());
            }else{
                postDTO.setPostID(0);
            }
            posts.add(postDTO);
        }
    }

}
