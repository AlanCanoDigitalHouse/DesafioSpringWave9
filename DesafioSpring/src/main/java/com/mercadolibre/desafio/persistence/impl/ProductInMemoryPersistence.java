package com.mercadolibre.desafio.persistence.impl;

import com.mercadolibre.desafio.dtos.RequestPostDto;
import java.time.temporal.ChronoUnit;
import com.mercadolibre.desafio.dtos.ResponseListPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.Post;
import com.mercadolibre.desafio.model.User;
import com.mercadolibre.desafio.persistence.ProductPersistence;
import com.mercadolibre.desafio.persistence.UserPersistence;
import com.mercadolibre.desafio.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductInMemoryPersistence implements ProductPersistence {

    Map<Integer, Post> database = new HashMap<>();

    @Autowired
    UserPersistence userPersistence;

    @Override
    public void addPostProduct(RequestPostDto requestPostDto) throws UserException {
        User user = userPersistence.getUserById(requestPostDto.getUserId());
        List<Integer> idPosts = user.getPosts();
        Integer id = database.values().size();
        Post post = new Post(id, requestPostDto.getDate()
                , requestPostDto.getDetail(), requestPostDto.getCategory()
                , requestPostDto.getPrice());

        idPosts.add(id);
        savePost(post);
    }

    @Override
    public void savePost(Post post) {
        database.put(post.getId_post(), post);
    }

    @Override
    public Post findPostById(Integer postId) throws PostException {
        Post post = database.get(postId);
        return Optional.ofNullable(post).orElseThrow(() -> new PostException(PostException.ID_NOT_FOUND));
    }

    @Override
    public ResponseListPost getPostsFollowed(Integer userId) throws UserException, PostException {
        User user = userPersistence.getUserById(userId);

        List<User> followed = new ArrayList<>();
        for(Integer id : user.getFollowed()){
            followed.add(userPersistence.getUserById(id));
        }

        List<Integer> idList = new ArrayList<>();
        followed.forEach(u->idList.addAll(u.getPosts()));

        List<Post> posts = getPosts(idList);

        posts.sort(Comparator.comparing(Post::getDate).reversed());


        List<Post> listFilter= posts.stream().filter(p-> DateUtils.weeksBetween(p.getDate(),new Date())<=2).collect(Collectors.toList());
        return new ResponseListPost(userId,listFilter);
    }

    public List<Post> getPosts(List<Integer> idList) throws PostException {
        List<Post> posts = new ArrayList<>();
        for (Integer id : idList){
            posts.add(findPostById(id));
        }
        return posts;
    }
}
