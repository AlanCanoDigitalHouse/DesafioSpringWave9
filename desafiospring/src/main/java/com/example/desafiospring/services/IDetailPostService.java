package com.example.desafiospring.services;


import com.example.desafiospring.dtos.DetailPostDTO;

public interface IDetailPostService {

    DetailPostDTO findDetailPostById(Integer productId);


}
