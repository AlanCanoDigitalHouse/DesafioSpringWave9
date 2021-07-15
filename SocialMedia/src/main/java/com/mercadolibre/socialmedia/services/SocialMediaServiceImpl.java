package com.mercadolibre.socialmedia.services;

import com.mercadolibre.socialmedia.dtos.PostDto;
import com.mercadolibre.socialmedia.dtos.User;
import com.mercadolibre.socialmedia.dtos.UserDto;
import com.mercadolibre.socialmedia.dtos.request.PostRequestDto;
import com.mercadolibre.socialmedia.dtos.response.FollowedUsersResponse;
import com.mercadolibre.socialmedia.dtos.response.FollowersQuantityResponse;
import com.mercadolibre.socialmedia.dtos.response.FollowersUsersResponse;
import com.mercadolibre.socialmedia.dtos.response.PostsUserResponse;
import com.mercadolibre.socialmedia.exceptions.FollowException;
import com.mercadolibre.socialmedia.exceptions.RequestParamException;
import com.mercadolibre.socialmedia.repositories.ISocialMediaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Comparator.comparing;

@Service
public class SocialMediaServiceImpl implements ISocialMediaService{
    ISocialMediaRepository iSocialMediaRepository;

    public SocialMediaServiceImpl(ISocialMediaRepository iSocialMediaRepository){
        this.iSocialMediaRepository = iSocialMediaRepository;
    }

    public ResponseEntity followUser(Integer userId, Integer userIdToFollow){
        if(userId.equals(userIdToFollow))
            throw new FollowException("The user cant autofollow");

        UserDto user = iSocialMediaRepository.findUserById(userId);
        UserDto userToFollow = iSocialMediaRepository.findUserById(userIdToFollow);

        this.addFollowed(user, userToFollow);
        this.addFollower(user, userToFollow);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO: Agregar Seguidor. Quien me sigue?
    private void addFollower(UserDto user, UserDto userToFollow){
        List<User> followers = userToFollow.getFollowers();
        followers.add(new User(user.getUserId(), user.getUserName()));
        userToFollow.setFollowers(followers);
    }

    //TODO: Agregar seguido. A quien sigo?
    private void addFollowed(UserDto user, UserDto userToFollow){
        List<User> followed = user.getFollowed();
        Boolean exist = followed.stream().anyMatch(usr -> usr.getUserId().equals(userToFollow.getUserId()));
        if(exist)
            throw new FollowException("The user: "+user.getUserId()+" alrready follow the user: "+ userToFollow.getUserId());
        followed.add(new User(userToFollow.getUserId(), userToFollow.getUserName()));
        user.setFollowed(followed);
    }

    private void sortAlphabetically(UserDto user, String order){
        if(order.equals("name_asc"))
            user.getFollowers().sort(comparing(User::getUserName, String.CASE_INSENSITIVE_ORDER));
        else if(order.equals("name_desc"))
            user.getFollowers().sort(comparing(User::getUserName, String.CASE_INSENSITIVE_ORDER).reversed());
        else
            throw new RequestParamException("The order: "+ order +" is not valid, The orders allowed are: name_asc, name_desc");
    }

    private void sortByDate(List<PostDto> postDtos, String order){
        if(order.equals("date_asc"))
            postDtos.sort(comparing(PostDto::getDate));
        else if(order.equals("date_desc"))
            postDtos.sort(comparing(PostDto::getDate).reversed());
        else
            throw new RequestParamException("The order: "+ order +" is not valid, The orders allowed are: date_asc, date_desc");
    }

    @Override
    public FollowersQuantityResponse countUserFollowers(int userId) {
        UserDto user = iSocialMediaRepository.findUserById(userId);
        Integer followersQuantity = user.getFollowers().size();
        return new FollowersQuantityResponse(user.getUserId(), user.getUserName(), followersQuantity);
    }

    @Override
    public FollowersUsersResponse getFollowersUserList(Integer userId, String order) {
        UserDto user = iSocialMediaRepository.findUserById(userId);
        this.sortAlphabetically(user, order);
        return  new FollowersUsersResponse(user.getUserId(), user.getUserName(), user.getFollowers());
    }

    @Override
    public FollowedUsersResponse getFollowedUserList(Integer userId, String order){
        UserDto user = iSocialMediaRepository.findUserById(userId);
        this.sortAlphabetically(user, order);
        return new FollowedUsersResponse(user.getUserId(), user.getUserName(), user.getFollowed());
    }

    private PostDto mapPost(PostRequestDto postRequestDto){
        PostDto post = new PostDto();
        post.setDate(postRequestDto.getDate());
        post.setDetail(postRequestDto.getDetail());
        post.setCategory(postRequestDto.getCategory());
        post.setPrice(postRequestDto.getPrice());
        return post;
    }

    @Override
    public ResponseEntity addNewPost(PostRequestDto postDto){
        UserDto user = iSocialMediaRepository.findUserById(postDto.getUserId());
        PostDto post = this.mapPost(postDto);
        List<PostDto> posts = user.getPosts();
        posts.add(post);
        user.setPosts(posts);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public PostsUserResponse getLastPostsByUser(Integer userId, String order){
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -14);
        UserDto user = iSocialMediaRepository.findUserById(userId);
        List<User> followed = user.getFollowed();
        List<PostDto> posts = new ArrayList<>(){};

        for(User user1:followed){
            posts.addAll( iSocialMediaRepository.getPosts(user1.getUserId()) );
        }

        List<PostDto> postsFiltered = this.filterPostByDate(posts, currentDate);

        this.sortByDate(postsFiltered, order);
        return new PostsUserResponse(userId, postsFiltered);
    }

    private List<PostDto> filterPostByDate(List<PostDto> posts, Calendar date){
        Date fecha = date.getTime();
        List<PostDto> postFiltered = new ArrayList<>();
        for(PostDto post: posts){
            if(post.getDate().after(fecha))
                postFiltered.add(post);
        }
        //TODO: Intente hacer lo de abajo pero por alguna razon no me funciono, tuve problemas con los tipos de Datos
        //posts.stream().filter(post-> post.getDate().compareTo(fecha) > 0).toArray();
        return postFiltered;
    }

    @Override
    public ResponseEntity unfollowUser(Integer userId, Integer userToUnfollow) {
        this.deleteFollowed(userId, userToUnfollow);
        this.deleteFollower(userId, userToUnfollow);
        return new ResponseEntity(HttpStatus.OK);
    }

    private void deleteFollower(Integer userId, Integer userToUnfollow){
        UserDto user = iSocialMediaRepository.findUserById(userToUnfollow);
        List<User> followers = user.getFollowers();
        followers.removeIf(userr -> userr.getUserId().equals(userId));
        user.setFollowers(followers);
    }

    private void deleteFollowed(Integer userId, Integer userToUnfollow){
        UserDto user = iSocialMediaRepository.findUserById(userId);
        List<User> followed = user.getFollowed();
        followed.removeIf(userr -> userr.getUserId().equals(userToUnfollow));
        user.setFollowed(followed);
    }
}
