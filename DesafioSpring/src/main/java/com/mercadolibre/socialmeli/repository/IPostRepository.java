package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Integer> {
    @Query(value ="SELECT * FROM post p WHERE p.user_id in (:userId) and p.create_at BETWEEN :startDate AND :endDate ORDER BY p.create_at DESC", nativeQuery = true)
    public List<Post> findByUserIdDESC(@Param("userId")List<Integer> userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value ="SELECT * FROM post p WHERE p.user_id in (:userId) and p.create_at BETWEEN :startDate AND :endDate ORDER BY p.create_at ASC", nativeQuery = true)
    public List<Post> findByUserIdASC(@Param("userId")List<Integer> userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value ="SELECT * FROM post p WHERE p.user_id in (:userId) and p.create_at BETWEEN :startDate AND :endDate ORDER BY p.create_at ", nativeQuery = true)
    public List<Post> findByUserIdIn(@Param("userId")List<Integer> userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
