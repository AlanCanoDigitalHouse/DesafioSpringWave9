package com.mercadolibre.desafio_spring.Services;

import com.mercadolibre.desafio_spring.Repositories.ISocialMeliRepository;
import com.mercadolibre.desafio_spring.UtilsSocialMeli.UtilsSocialMediaMeli;
import com.mercadolibre.desafio_spring.dtos.request.NewPostRequest;
import com.mercadolibre.desafio_spring.dtos.request.PromoPostRequest;
import com.mercadolibre.desafio_spring.dtos.response.*;
import com.mercadolibre.desafio_spring.entities.Post;
import com.mercadolibre.desafio_spring.entities.PromoPost;
import com.mercadolibre.desafio_spring.entities.User;
import com.mercadolibre.desafio_spring.exceptions.AlreadyExistError;
import com.mercadolibre.desafio_spring.exceptions.IdNotFound;
import com.mercadolibre.desafio_spring.exceptions.SortedMethodError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SocialMeliService implements ISocialMeliService {

    @Autowired
    ISocialMeliRepository repository;

    //001 seguir a un determinado vendedor
    @Override
    public HttpStatus follow(int userId, int userIdToFollow) throws IdNotFound, AlreadyExistError {
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        Optional<User> userFollower = repository.findUserById(userId);
        if(userFollower.isPresent()){
            Optional<User> userToFollow = repository.findUserById(userIdToFollow);
            if(userToFollow.isPresent()){
                for(User userEx: userFollower.get().getFollowing()){
                    if(userEx.getUserId().equals(userToFollow.get().getUserId()))
                        throw new AlreadyExistError();
                }
                userFollower.get().getFollowing().add(userToFollow.get());
                userToFollow.get().getFollowers().add(userFollower.get());
                repository.putUser(userFollower.get());
                repository.putUser(userToFollow.get());
                responseStatus = HttpStatus.OK;
            }else throw new IdNotFound();
        } else throw new IdNotFound();
        return responseStatus;
    }

    //002 cantidad de usuarios que siguen a un determinado vendedor
    @Override
    public FollowersCountResponse followersCount(int userId) throws IdNotFound {
        Optional<User> user =repository.findUserById(userId);
        FollowersCountResponse followersCountResponse = null;
        if(user.isPresent()) {
            followersCountResponse= new FollowersCountResponse(
                    user.get().getUserId(),
                    user.get().getUserName(),
                    user.get().getFollowers().size()
            );
        }else throw new IdNotFound();
        return followersCountResponse;
    }

    //003 ¿Quien me sigue?
    @Override
    public FollowersListResponse followersList(int userId) throws IdNotFound{
        Optional<User> user = repository.findUserById(userId);
        FollowersListResponse followersListResponse = null;
        if(user.isPresent()){
            ArrayList<UserEntitieResponse> listFollowers = new ArrayList<>();
            for(User follower: user.get().getFollowers()){
                listFollowers.add( new UserEntitieResponse(follower.getUserId(),follower.getUserName()));
            }
            followersListResponse = new FollowersListResponse(
                    userId,
                    user.get().getUserName(),
                    listFollowers
            );
        } else throw new IdNotFound();
        return followersListResponse;
    }

    //004 ¿A quién sigo?
    @Override
    public FollowedListResponse followedList(int UserId) throws IdNotFound {
        Optional<User> user = repository.findUserById(UserId);
        FollowedListResponse followedListResponse = null;
        if(user.isPresent()){
            ArrayList<UserEntitieResponse> listFollowed = new ArrayList<>();
            for(User followed: user.get().getFollowing()){
                listFollowed.add(new UserEntitieResponse(
                    followed.getUserId(),followed.getUserName()
                ));
            }
            followedListResponse = new FollowedListResponse(
                    UserId,
                    user.get().getUserName(),
                    listFollowed
            );
        }else throw new IdNotFound();
        return followedListResponse;
    }

    //005 Nuevo Post Normal
    @Override
    public HttpStatus newPost(NewPostRequest newPostRequest) throws IdNotFound, AlreadyExistError{
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        Optional<User> user = repository.findUserById(newPostRequest.getUserId());
        if(user.isPresent()){
            for(Post oldPost :user.get().getPosts())
                if(newPostRequest.getId_post().equals(oldPost.getId_post()))
                    throw new AlreadyExistError();

            user.get().getPosts().add(new Post(
                    newPostRequest.getUserId(),
                    newPostRequest.getId_post(),
                    newPostRequest.getDate(),
                    newPostRequest.getDetail(),
                    newPostRequest.getCategory(),
                    newPostRequest.getPrice()
            ));
            responseStatus = HttpStatus.OK;
        } else throw new IdNotFound();
        return responseStatus;
    }

    //006
    @Override
    public ListPostByUserResponse listPostUser(int userId) throws IdNotFound{
        ListPostByUserResponse listPostByUserResponse = null;
        Optional<User> user = repository.findUserById(userId);
        if(user.isPresent()){
            ArrayList<Post> listPost= new ArrayList<>();
            //Traer Usuarios a los vendedores que sigue el usuario
            for(User follower: user.get().getFollowing()) {
                //Traer los post de esos vendedores
                for(Post post: follower.getPosts()) {
                    //Filtro dos semanas
                    if (Period.between(UtilsSocialMediaMeli.toLocalDate(post.getDate()), LocalDate.now()).getDays() < 14) {
                        listPost.add(post);
                    }
                }
            }
            listPostByUserResponse = new ListPostByUserResponse(
                    userId,
                    listPost
            );
        }else throw new IdNotFound();
        return listPostByUserResponse;
    }

    //007 dejar de seguir a un determiando vendedor
    @Override
    public void unfollow(int userId, int userIdToUnfollow) throws IdNotFound{
        Optional<User> userFollower = repository.findUserById(userId);
        if (userFollower.isPresent()) {
            Optional<User> userToUnfollow = repository.findUserById(userIdToUnfollow);
            if (userToUnfollow.isPresent()) {
                userFollower.get().getFollowing().remove(userToUnfollow.get());
                userToUnfollow.get().getFollowers().remove(userFollower.get());
                repository.putUser(userFollower.get());
                repository.putUser(userToUnfollow.get());
            }else throw new IdNotFound();
        }else throw new IdNotFound();
    }

    //008 ordenamiento alfabetico por nombres de usuarios que siguen a otro usuario
    @Override
    public void sortedFollowersUser(int userId, String sortedMet) throws SortedMethodError, IdNotFound {
        Optional<User> user = repository.findUserById(userId);
        if (user.isPresent()) {
            if(sortedMet.equals("name_asc")){
                List<User> tempUsers = user.get()
                        .getFollowers()
                        .stream()
                        .sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList());
                user.get().setFollowers((ArrayList<User>) tempUsers);
            }
            else if(sortedMet.equals("name_desc"))
            {
                List<User> tempUsers = user.get()
                        .getFollowers()
                        .stream()
                        .sorted(Comparator.comparing(User::getUserName).reversed()).collect(Collectors.toList());
                user.get().setFollowers((ArrayList<User>) tempUsers);
            } else throw new SortedMethodError();

            repository.putUser(user.get());
        }else throw new IdNotFound();
    }

    //008 ordenamiento alfabetico por nombres de
    @Override
    public void sortedFollowedUser(int userId, String sortedMet) throws SortedMethodError, IdNotFound {
        Optional<User> user = repository.findUserById(userId);
        if (user.isPresent()) {
            if (sortedMet.equals("name_asc")) {
                List<User> tempUsers = user.get()
                        .getFollowing()
                        .stream()
                        .sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList());
                user.get().setFollowing((ArrayList<User>) tempUsers);
            }
            else if (sortedMet.equals("name_desc")) {
                List<User> tempUsers = user.get()
                        .getFollowing()
                        .stream()
                        .sorted(Comparator.comparing(User::getUserName).reversed()).collect(Collectors.toList());
                user.get().setFollowers((ArrayList<User>) tempUsers);
            }
            else throw new SortedMethodError();
            repository.putUser(user.get());
        }else throw new IdNotFound();
    }

    @Override
    public void sortedPostUser(int userId, String sortedMet) throws SortedMethodError, IdNotFound{
        Optional<User> user = repository.findUserById(userId);
        if (user.isPresent()) {
            if (sortedMet.equals("date_asc")) {
                List<Post> tempPosts = user.get()
                        .getPosts()
                        .stream()
                        .sorted(Comparator.comparing(Post::getDate)).collect(Collectors.toList());
                user.get().setPosts((ArrayList<Post>) tempPosts);
            }
            else if (sortedMet.equals("date_desc")) {
                List<Post> tempPosts = user.get()
                        .getPosts()
                        .stream()
                        .sorted(Comparator.comparing(Post::getDate).reversed()).collect(Collectors.toList());
                user.get().setPosts((ArrayList<Post>) tempPosts);
            }
            else throw new SortedMethodError();
            repository.putUser(user.get());
        } else throw new IdNotFound();
    }

    @Override
    public HttpStatus newPromoPost(PromoPostRequest promoPostRequest) throws IdNotFound, AlreadyExistError{
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        Optional<User> user = repository.findUserById(promoPostRequest.getUserId());
        if(user.isPresent()){
            for(Post oldPost :user.get().getPosts())
                if(promoPostRequest.getId_post().equals(oldPost.getId_post()))
                    throw new AlreadyExistError();
            user.get().getPosts().add(new PromoPost(
                    promoPostRequest.getUserId(),
                    promoPostRequest.getId_post(),
                    promoPostRequest.getDate(),
                    promoPostRequest.getDetail(),
                    promoPostRequest.getCategory(),
                    promoPostRequest.getPrice(),
                    promoPostRequest.isHasPromo(),
                    promoPostRequest.getDiscount()
            ));
            responseStatus = HttpStatus.OK;
        } else throw new IdNotFound();
        return responseStatus;
    }

    @Override
    public PromoProductsCountResponse getPromoProductsCount(int userId) throws IdNotFound{
        PromoProductsCountResponse response = null;
        Optional<User> user = repository.findUserById(userId);
        if(user.isPresent()){
            ArrayList<PromoPost> posts = new ArrayList<>();
            for(Post post: user.get().getPosts()) {
                if(post instanceof PromoPost ){
                     posts.add((PromoPost) post);
                }
            }

            response = new PromoProductsCountResponse(
                    userId,
                    user.get().getUserName(),
                    posts.size()
            );
        }else throw new IdNotFound();
        return response;
    }

    @Override
    public ListPromoProductsResponse getPromoProductsList(int userid) throws IdNotFound {
        ListPromoProductsResponse response = null;
        Optional<User> user = repository.findUserById(userid);
        if(user.isPresent()){
            ArrayList<PromoPost> posts = new ArrayList<>();
            for(Post post: user.get().getPosts()) {
                if(post instanceof PromoPost ){
                    posts.add((PromoPost) post);
                }
            }

            response = new ListPromoProductsResponse(
                    userid,
                    user.get().getUserName(),
                    posts
            );
        }else throw new IdNotFound();
        return response;
    }

}
