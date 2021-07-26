package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IFollowRepository extends JpaRepository<Follow, Integer > {
    @Query(value ="SELECT COUNT(*) FROM follow f WHERE f.user_id = ?1 and f.follow_status = true", nativeQuery = true)
    Integer countByUserIdToFollow(Integer id);

    @Query(value ="SELECT * FROM follow f WHERE f.user_id = ?1 and f.follow_status = true", nativeQuery = true)
    List<Follow> findByUserIdToFollow(Integer id);

    @Query(value ="SELECT * FROM follow f WHERE f.user_id_to_follow = ?1 and f.follow_status = true", nativeQuery = true)
    List<Follow> findByUserId(Integer id);

    @Query(value ="SELECT * FROM follow f WHERE f.user_id = ?1 and f.user_id_to_follow = ?2", nativeQuery = true)
    Follow findFollowByUserIdAndUserIdToFollow(Integer userId, Integer userIdToFollow);



}
