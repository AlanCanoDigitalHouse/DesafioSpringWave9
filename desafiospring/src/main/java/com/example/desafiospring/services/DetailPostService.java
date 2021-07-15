package com.example.desafiospring.services;

import com.example.desafiospring.dtos.DetailPostDTO;
import com.example.desafiospring.repositories.IDetailPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailPostService implements IDetailPostService{

    @Autowired
    private IDetailPost iDetailPost;

    @Override
    public DetailPostDTO findDetailPostById(Integer productId) {
        DetailPostDTO detailById = this.iDetailPost.findDetailById(productId);
        if(detailById != null){
            return detailById;
        }else{
            return null;
        }
    }
}
