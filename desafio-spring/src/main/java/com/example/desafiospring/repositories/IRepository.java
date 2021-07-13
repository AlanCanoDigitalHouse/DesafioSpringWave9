package com.example.desafiospring.repositories;

import java.util.Collection;

public interface IRepository<T, ID, E extends Exception> {
    T findById(ID id) throws E;

    Collection<T> findByIds(Collection<ID> ids);
}
