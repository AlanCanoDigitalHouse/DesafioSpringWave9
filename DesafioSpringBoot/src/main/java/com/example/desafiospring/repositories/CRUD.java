package com.example.desafiospring.repositories;

import java.util.List;

public interface CRUD<Response> {
    Response getById(Integer id);
    List<Response> getAll();
    Response create(Response element);
    Boolean update(Response newElement);
    boolean delete(Integer id);
    boolean delete(Response element);
}
