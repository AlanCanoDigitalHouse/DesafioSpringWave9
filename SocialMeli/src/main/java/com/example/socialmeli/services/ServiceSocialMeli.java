package com.example.socialmeli.services;

import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.dtos.request.PostDto;
import com.example.socialmeli.dtos.response.FollowedListResponseDto;
import com.example.socialmeli.dtos.response.FollowersCountResponseDto;
import com.example.socialmeli.dtos.response.FollowersListResponseDto;
import com.example.socialmeli.dtos.response.PostResponseDto;
import com.example.socialmeli.exceptions.CantFollowYourselfException;
import com.example.socialmeli.exceptions.OrderNotFoundException;
import com.example.socialmeli.exceptions.UserNotFoundException;
import com.example.socialmeli.models.User;
import com.example.socialmeli.repositories.IRepositorySocialMeli;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class ServiceSocialMeli implements IServiceSocialMeli {

    IRepositorySocialMeli repositorySocialMeli;

    public ServiceSocialMeli(IRepositorySocialMeli repositorySocialMeli) {
        this.repositorySocialMeli = repositorySocialMeli;
    }

    @Override
    public List<User> create() {
       return repositorySocialMeli.create();
    }


    @Override
    public void follow(Integer userId, Integer userIdToFollow) throws UserNotFoundException, CantFollowYourselfException {
        if(userId.equals(userIdToFollow)){
            throw new CantFollowYourselfException("Los ids ingresados son iguales, no podes seguirte a vos mismo");
        }else {
            repositorySocialMeli.saveFollow(userId,userIdToFollow);
        }
    }


    @Override
    public FollowersCountResponseDto countFollower(Integer userId) throws UserNotFoundException {
        Optional<User> user =repositorySocialMeli.findUserById(userId);
        FollowersCountResponseDto followersCount=new FollowersCountResponseDto();

        if(user.isPresent()){
            followersCount.setFollowers_count(user.get().countFollowers());
            followersCount.setUserId(userId);
            followersCount.setUserName(user.get().getUserName());
        }else{
           throw new UserNotFoundException("El usuario no se encontro");
        }
        return followersCount;
    }

    @Override
    public FollowersListResponseDto getFollowers(Integer userID, String order) throws OrderNotFoundException {
        Optional<User> user=repositorySocialMeli.findUserById(userID);

        FollowersListResponseDto followersList=new FollowersListResponseDto
                    (user.get().getUserId(),user.get().getUserName(),user.get().getFollowers());

        getNameOrder(followersList.getFollowers(),order);

        return followersList;
    }

    @Override
    public FollowedListResponseDto followedList(Integer userId,String order) throws UserNotFoundException, OrderNotFoundException {
        Optional<User> user=repositorySocialMeli.findUserById(userId);
        FollowedListResponseDto followedList=new FollowedListResponseDto();

        if(user.isPresent()){
            followedList.setUserId(userId);
            followedList.setUserName(user.get().getUserName());
            followedList.setFollowed(user.get().getFollowed());
            getNameOrder(followedList.getFollowed(),order);
        }else{
              throw new UserNotFoundException("El usuario no se encontro");
        }
        return followedList;
    }

    @Override
    public List<User> unfollow(Integer userId, Integer userIdToUnfollow) throws UserNotFoundException {
        return repositorySocialMeli.saveUnfollow(userId,userIdToUnfollow);
    }

    @Override
    public HttpStatus saveNewPost(PostDto postDto) throws UserNotFoundException {
        return repositorySocialMeli.saveNewPost(postDto);
    }

    @Override
    public PostResponseDto getPost(Integer userId,String order) throws UserNotFoundException, OrderNotFoundException {
        PostResponseDto postResponseDto=new PostResponseDto();
        postResponseDto.setUserId(userId);
        postResponseDto.setPosts(repositorySocialMeli.getPost(userId));
        getPostOrdered(postResponseDto.getPosts(),order);
        return postResponseDto;
    }

    @Override
    public List<UserDto> getNameOrder(List<UserDto> ListToOrder, String order) throws OrderNotFoundException {

        if(order.equals("name_asc")){
            Collections.sort(ListToOrder,Comparator.comparing(UserDto::getUserName));

        }else if(order.equals("name_desc")){
            Collections.sort(ListToOrder,Comparator.comparing(UserDto::getUserName).reversed());
       }else {
            throw new OrderNotFoundException("El metodo de ordenamiento ingresao no es correcto");
        }
        return ListToOrder;
    }


    @Override
    public List<PostDto> getPostOrdered(List<PostDto> postsToOrder, String order) throws OrderNotFoundException {

        if(order.equals("date_desc")){
            postsToOrder.sort((Comparator.comparing(post -> post.getDate())));
        }else if(order.equals("date_asc")){
            Collections.sort(postsToOrder);
        }else {
            throw new OrderNotFoundException("El metodo de ordenamiento ingresao no es correcto");
        }
        return postsToOrder;

    }



}
