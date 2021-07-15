package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.DetailPostDTO;

public interface IDetailPost {

    DetailPostDTO findDetailById(Integer productId);

}
