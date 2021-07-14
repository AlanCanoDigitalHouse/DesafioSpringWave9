package com.mercadolibre.desafio.persistence.impl;

import com.mercadolibre.desafio.dtos.requests.RequestPostDiscountDto;
import com.mercadolibre.desafio.dtos.requests.RequestPostDto;
import com.mercadolibre.desafio.dtos.responses.ResponseCountPromo;
import com.mercadolibre.desafio.dtos.responses.ResponseListPost;
import com.mercadolibre.desafio.dtos.responses.ResponseListPromoPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.Post;
import com.mercadolibre.desafio.model.PostDiscount;
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
    public ResponseListPost getPostsFollowed(Integer userId, String order) throws UserException, PostException {
        User user = userPersistence.getUserById(userId);
        // Get the users that userid follow
        List<User> followed = getUsers(user.getFollowed());
        //ids list of posts of users followed
        List<Integer> idPostList = new ArrayList<>();
        followed.forEach(u -> idPostList.addAll(u.getPosts()));
        //posts list of users followed
        List<Post> posts = getPosts(idPostList);
        //filter post
        List<Post> listFilter = posts.stream().filter(p -> DateUtils.weeksBetween(p.getDate(), new Date()) <= 2).collect(Collectors.toList());
        listFilter.sort(Comparator.comparing(Post::getDate).reversed());
        if (order != null) {
            orderByDate(listFilter, order);
        }
        return new ResponseListPost(userId, listFilter);
    }

    @Override
    public void addPostDiscountProduct(RequestPostDiscountDto requestPostDiscountDto) throws UserException {
        User user = userPersistence.getUserById(requestPostDiscountDto.getUserId());
        List<Integer> idPosts = user.getPosts();
        Integer id = database.values().size();
        Post post = new PostDiscount(id, requestPostDiscountDto.getDate()
                , requestPostDiscountDto.getDetail(), requestPostDiscountDto.getCategory()
                , requestPostDiscountDto.getPrice(), requestPostDiscountDto.isHasPromo(), requestPostDiscountDto.getDiscount());

        idPosts.add(id);
        savePost(post);

    }

    @Override
    public ResponseCountPromo getCountPromo(Integer userId) throws UserException, PostException {
        User user = userPersistence.getUserById(userId);
        List<Post> posts = getPosts(user.getPosts());
        int count = (int) posts.stream().filter(p -> p instanceof PostDiscount).count();
        return new ResponseCountPromo(user.getUserID(), user.getUserName(), count);
    }

    @Override
    public ResponseListPromoPost getPromoPosts(Integer userId) throws UserException, PostException {
        User user = userPersistence.getUserById(userId);
        List<Post> posts = getPosts(user.getPosts());
        List<Post> postsFilter = posts.stream().filter(p -> p instanceof PostDiscount).collect(Collectors.toList());
        return new ResponseListPromoPost(user.getUserID(), user.getUserName(), postsFilter);
    }

    public void orderByDate(List<Post> postList, String order) {
        if (order.equals("date_asc")) {
            postList.sort(Comparator.comparing(Post::getDate));
        } else if (order.equals("date_desc")) {
            postList.sort(Comparator.comparing(Post::getDate).reversed());
        }
    }

    public List<User> getUsers(List<Integer> listId) throws UserException {
        List<User> followed = new ArrayList<>();
        for (Integer id : listId) {
            followed.add(userPersistence.getUserById(id));
        }
        return followed;
    }

    public List<Post> getPosts(List<Integer> idList) throws PostException {
        List<Post> posts = new ArrayList<>();
        for (Integer id : idList) {
            posts.add(findPostById(id));
        }
        return posts;
    }

    @Override
    public Post findPostById(Integer postId) throws PostException {
        Post post = database.get(postId);
        return Optional.ofNullable(post).orElseThrow(() -> new PostException(PostException.ID_NOT_FOUND));
    }

    @Override
    public void savePost(Post post) {
        database.put(post.getId_post(), post);
    }
}
