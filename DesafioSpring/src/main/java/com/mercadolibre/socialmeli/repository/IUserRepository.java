package com.mercadolibre.socialmeli.repository;


import com.mercadolibre.socialmeli.entity.Follow;
import com.mercadolibre.socialmeli.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository  extends JpaRepository<User,Integer> {
    public List<User> findByUserId(Integer id);
}
