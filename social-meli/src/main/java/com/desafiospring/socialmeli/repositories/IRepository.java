package com.desafiospring.socialmeli.repositories;

public interface IRepository<T> {

    T add(T item);

    T get(int itemId);

    T update(T item);

    void delete(int itemId);
}
