package com.desafiospring.socialmeli.repositories;

import java.util.List;

public interface IRepository<T> {

    T add(T item);

    T get(int itemId);

    List<T> getAll();

    T update(T item);

    void delete(int itemId);
}
