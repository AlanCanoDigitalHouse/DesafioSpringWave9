package com.example.desafiospring.repositories;

public interface CRUD <Response>{
    Response create(Response element);
    void update (Response element);
    Response getById(Integer id);
    void delete(Integer id);
}
