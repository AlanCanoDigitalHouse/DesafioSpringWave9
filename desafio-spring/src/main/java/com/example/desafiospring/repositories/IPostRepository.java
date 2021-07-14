package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.models.Post;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IPostRepository extends IRepository<Post, Long, PostException> {
    Long addPost(Post post) throws PostException;

    List<Post> findPromoPostsBySellerId(Long sellerId);

    List<Post> findBySellerId(Long sellerId);

    List<Post> findBySellersIds(Collection<Long> sellersIds);

    List<Post> getPostAfterDateBySellersIds(Collection<Long> sellersIds, Date date);

}
