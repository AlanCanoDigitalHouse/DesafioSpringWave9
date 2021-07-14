package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.models.Post;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IPostRepository extends IRepository<Post, Integer, PostException> {
    Integer addPost(Post post);

    List<Post> findPromoPostsBySellerId(Integer sellerId);

    List<Post> findBySellerId(Integer sellerId);

    List<Post> findBySellersIds(Collection<Integer> sellersIds);

    List<Post> getPostAfterDateBySellersIds(Collection<Integer> sellersIds, Date date);

}
