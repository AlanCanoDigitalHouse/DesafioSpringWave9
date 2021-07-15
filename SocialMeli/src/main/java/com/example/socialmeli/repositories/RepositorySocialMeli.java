package com.example.socialmeli.repositories;

import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.dtos.request.PostDto;
import com.example.socialmeli.exceptions.UserNotFoundException;
import com.example.socialmeli.handlers.UserCreationHandler;
import com.example.socialmeli.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class RepositorySocialMeli implements IRepositorySocialMeli {

    private final List<User> users=new ArrayList<>();


    public List<User> create() {
        return UserCreationHandler.create(users);

    }


    public void saveFollow(Integer userId, Integer userIdToFollow) throws UserNotFoundException {
        Optional<User> userToFollow=findUserById(userIdToFollow);
        Optional<User> userWhoFollows=findUserById(userId);

        if(userToFollow.isPresent() && userWhoFollows.isPresent()){
            userToFollow.get().addFollower(new UserDto(userWhoFollows.get().getUserId(),userWhoFollows.get().getUserName()));
            userWhoFollows.get().addFollowed(new UserDto(userToFollow.get().getUserId(),userToFollow.get().getUserName()));
        }else{
              throw new UserNotFoundException("El usuario no se encontro");
        }

    }

    public List<User> saveUnfollow(Integer userId, Integer userIdToUnfollow) throws UserNotFoundException {
        Optional<User> actualUser=findUserById(userId);
        Optional<User> userToUnfollow= findUserById(userIdToUnfollow);

        if(actualUser.isPresent() && userToUnfollow.isPresent()){

            actualUser.get().removeFollowed(new UserDto(userToUnfollow.get().getUserId(),userToUnfollow.get().getUserName()));
            userToUnfollow.get().removeFollower(new UserDto(actualUser.get().getUserId(),actualUser.get().getUserName()));
        }else{
              throw new UserNotFoundException("El usuario no se encontro");
        }

        return users;
    }


    @Override
    public HttpStatus saveNewPost(PostDto postDto) throws UserNotFoundException {
        Optional<User> user=findUserById(postDto.getUserId());
        if(user.isPresent()){
            user.get().addPost(postDto);
            //users.get(user.get().getUserId()).setPosts(postDto);
            //users.get(userWhoFollowed.get().getUserId()).getPosts().setDetail(postDto.getDetail());
        }else{
            throw new UserNotFoundException("El usuario no se encontro");
        }
       return HttpStatus.OK;
    }

    public LocalDate calculateDate(){
        LocalDate returnvalue= LocalDate.now().minusDays(15);
        return returnvalue;
    }

    @Override
    public List<PostDto> getPost(Integer userId) throws UserNotFoundException {
        Optional<User> user=findUserById(userId);
        List<PostDto> posts=new ArrayList<>();

        if(user.isPresent()){
            for (UserDto u: user.get().getFollowed()){ // buscar los seguidos de ese
                Optional<User> userFollowed=findUserById(u.getUserId());
                for (PostDto p: userFollowed.get().getPosts()){ //traer los posts
                    if(p.getDate().isAfter(calculateDate())){
                        posts.add(p);
                    }

                }

            }
        }else{
              throw new UserNotFoundException("El usuario no se encontro");
        }
        return posts;
    }

    @Override
    public Optional<User> findUserById(Integer userId) {
        return users.stream().filter((u)->u.getUserId().equals(userId)).findFirst();

        }



    }





