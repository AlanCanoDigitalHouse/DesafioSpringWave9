package com.mercadolibre.desafio.spring.service;

import com.mercadolibre.desafio.spring.dtos.request.PostDto;
import com.mercadolibre.desafio.spring.dtos.response.NumberFollowersResponseDto;
import com.mercadolibre.desafio.spring.dtos.request.UserDto;
import com.mercadolibre.desafio.spring.dtos.response.PostResponseDto;
import com.mercadolibre.desafio.spring.dtos.response.UserFollowedResponseDto;
import com.mercadolibre.desafio.spring.dtos.response.UserFollowersResponseDto;
import com.mercadolibre.desafio.spring.exceptions.EmptyListException;
import com.mercadolibre.desafio.spring.exceptions.ErrorUserException;
import com.mercadolibre.desafio.spring.exceptions.InvalidPostException;
import com.mercadolibre.desafio.spring.exceptions.InvalidUserException;
import com.mercadolibre.desafio.spring.repositories.IRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppSocialMeliService implements IService {

    private final IRepository repository;
    private final List<UserDto> usersList;
    private final List<PostDto> postsList;

    public AppSocialMeliService(IRepository repository) {
        this.repository = repository;
        usersList = repository.loadUsersDatabase();
        postsList = repository.loadPostsDatabase();
    }

    @Override
    public void followUser(Integer userId, Integer userIdToFollow) {
        UserDto user = validateUser(userId);
        UserDto userToFollow = validateUser(userIdToFollow);

        if (userId.equals(userIdToFollow)) {
            throw new InvalidUserException("The users are equals");
        }

        user.getFollowed().add(userIdToFollow);    //The user to follow id is added to the list of followed
        userToFollow.getFollowers().add(userId);   //The user Id is added to the list of followers
        repository.writeDataBase("src/main/resources/users.json", usersList);
    }

    /**
     * Search that the user exists and return the amount of followers
     */
    @Override
    public NumberFollowersResponseDto getNumberOfUsers(Integer userId) {
        UserDto user = validateUser(userId);
        NumberFollowersResponseDto response = new NumberFollowersResponseDto();
        int numFollowers = user.getFollowers().size();
        if (numFollowers == 0) {
            throw new ErrorUserException("The user has no followers");
        }
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setFollowers_count(numFollowers);
        return response;
    }

    /**
     * Show the followers list
     */
    @Override
    public UserFollowersResponseDto showListFollowers(Integer userId, String sortOrder) {
        UserDto user = validateUser(userId);

        UserFollowersResponseDto response = new UserFollowersResponseDto();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setFollowers(new ArrayList<>());

        if (user.getFollowers().isEmpty()) {
            throw new EmptyListException("The followers list of the user " + user.getUserId() + " is empty");
        }
        user.getFollowers().forEach(integer -> {
            UserDto userFollower = repository.findUserById(integer);
            userFollower.setFollowers(null);
            userFollower.setFollowed(null);
            response.getFollowers().add(userFollower);
        });
        sorterByUserName(response.getFollowers(), sortOrder);
        return response;
    }

    /**
     * Method to validate users
     */

    private UserDto validateUser(Integer userId) {
        return usersList.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new InvalidUserException("The user Id " + userId + " does not exist"));
    }

    /**
     * Show the followed list
     */
    @Override
    public UserFollowedResponseDto showListFollowed(Integer userId, String sortOrder) {
        UserDto user = validateUser(userId);
        UserFollowedResponseDto response = new UserFollowedResponseDto();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setFollowed(new ArrayList<>());
        if (user.getFollowed().isEmpty()) {
            throw new EmptyListException("The followed list of the user" + user.getUserId() + " is empty");
        }
        user.getFollowed().forEach(integer -> {
            UserDto userFollowed = repository.findUserById(integer);
            userFollowed.setFollowers(null);
            userFollowed.setFollowed(null);
            response.getFollowed().add(userFollowed);
        });
        sorterByUserName(response.getFollowed(), sortOrder);
        return response;
    }

    /**
     * Add post to list and load the data in the json file
     */
    @Override
    public void createNewPost(PostDto post) {
        validateUser(post.getUserId());
        postsList.add(post);
        repository.writeDataBase("src/main/resources/posts.json", postsList);
    }

    /**
     * @param userId
     * @param userIdToUnFollow Validates if the userId exists and then unfollow the user
     */
    @Override
    public void unFollowUser(Integer userId, Integer userIdToUnFollow) {
        UserDto user = validateUser(userId);
        UserDto userToUnfollow = validateUser(userIdToUnFollow);

        if (userId.equals(userIdToUnFollow)) {
            throw new InvalidUserException("The users are equals");
        }
        user.getFollowed().removeIf(u -> u.equals(userIdToUnFollow));
        userToUnfollow.getFollowers().removeIf(u -> u.equals(userId));

        repository.writeDataBase("src/main/resources/users.json", usersList);
    }

    @Override
    public PostResponseDto getPosts(Integer userId, String order) {
        UserDto user = validateUser(userId);
        PostResponseDto response = new PostResponseDto();
        response.setUserId(user.getUserId());

        if (user.getFollowed().isEmpty()) {
            throw new InvalidUserException("The user does not have any followed");
        }
        user.getFollowed().forEach(integer -> postsList.stream()
                .filter(p -> p.getUserId().equals(integer))
                .forEach(x -> {
                    Date dateFilter = setDateFilterDaysPosts(14);
                    if (x.getDate().after(dateFilter)) {
                        response.getPosts().add(x);
                    }
                }));

        if (response.getPosts().isEmpty()) {
            throw new InvalidPostException("There is no post to show");
        }
        return sorterByPost(response, order);
    }

    /**
     * Sets a filter from the current date to a certain earlier date
     */
    private Date setDateFilterDaysPosts(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -days - 1);
        return calendar.getTime();
    }

    /**
     * @param postDisordered
     * @param sortOrder
     * @return postDisordered
     * Ordering by date of each post
     */
    private PostResponseDto sorterByPost(PostResponseDto postDisordered, String sortOrder) {
        switch (sortOrder) {
            case "date_asc":
                postDisordered.getPosts().sort(Comparator.comparing(PostDto::getDate).reversed());
                break;
            case "date_desc":
                postDisordered.getPosts().sort(Comparator.comparing(PostDto::getDate));
                break;
        }
        return postDisordered;
    }

    /**
     * @param userDisordered
     * @param sortOrder      Ordering by user name of each post
     */
    private void sorterByUserName(ArrayList<UserDto> userDisordered, String sortOrder) {
        switch (sortOrder) {
            case "name_asc":
                userDisordered.sort(Comparator.comparing(UserDto::getUserName));
                break;
            case "name_desc":
                userDisordered.sort(Comparator.comparing(UserDto::getUserName).reversed());
                break;
        }
    }
}