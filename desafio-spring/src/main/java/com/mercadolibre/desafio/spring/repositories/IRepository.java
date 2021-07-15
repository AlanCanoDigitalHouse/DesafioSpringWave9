package com.mercadolibre.desafio.spring.repositories;

import com.mercadolibre.desafio.spring.dtos.request.PostDto;
import com.mercadolibre.desafio.spring.dtos.request.UserDto;
import java.util.List;

public interface IRepository {

    UserDto findUserById(Integer id);
    List<UserDto> loadUsersDatabase();
    List<PostDto> loadPostsDatabase();
    void writeDataBase(String path, List<?> list);
}
