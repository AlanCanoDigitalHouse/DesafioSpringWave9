package com.meli.desafiospring.services;

import com.meli.desafiospring.DTOs.request.PromoPostRequestDTO;
import com.meli.desafiospring.DTOs.response.PostResponseDTO;
import com.meli.desafiospring.DTOs.request.PostRequestDTO;
import com.meli.desafiospring.DTOs.response.*;
import com.meli.desafiospring.exceptions.custom.BadDateFormat;
import com.meli.desafiospring.exceptions.custom.NoPostsAvailableException;
import com.meli.desafiospring.exceptions.custom.UserDoesNotExist;
import com.meli.desafiospring.models.User;
import com.meli.desafiospring.services.sorters.SortByNameAsc;
import com.meli.desafiospring.repositories.UserRepository;
import com.meli.desafiospring.services.sorters.Sorter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class UserManager implements IUserManager{

    UserRepository userRepo;
    //Atomic para post_id product_id
    AtomicLong id_post = new AtomicLong(0);
    AtomicLong product_id = new AtomicLong(0);

    public UserManager(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity<String> follow(Integer userId, Integer userIdToFollow) {
        Optional<User> followerOptional = userRepo.findById(userId);
        if (followerOptional.isEmpty())
            throw new UserDoesNotExist(userId);
        Optional<User> followedOptional = userRepo.findById(userIdToFollow);
        if (followedOptional.isEmpty())
            throw new UserDoesNotExist(userIdToFollow);

        User follower = followerOptional.get();
        User followed = followedOptional.get();
        follower.follow(followed);
        String okMessage = follower.getUserName()+ " followed " +followed.getUserName();
        return new ResponseEntity<>(okMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> unfollow(Integer userId, Integer userIdToUnfollow) {
        Optional<User> unfollowerOptional = userRepo.findById(userId);
        if (unfollowerOptional.isEmpty())
            throw new UserDoesNotExist(userId);
        Optional<User> unfollowedOptional = userRepo.findById(userIdToUnfollow);
        if (unfollowedOptional.isEmpty())
            throw new UserDoesNotExist(userIdToUnfollow);

        User unfollower = unfollowerOptional.get();
        User unfollowed = unfollowedOptional.get();
        unfollower.unfollow(unfollowed);
        String okMessage = unfollower.getUserName()+ " unfollowed " +unfollowed.getUserName();
        return new ResponseEntity<>(okMessage, HttpStatus.OK);
    }

    @Override
    public User getUser(Integer userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public FollowersCountResponseDTO followersCount(Integer userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty())
            throw new RuntimeException("El usuario no existe.");
        return new FollowersCountResponseDTO(
                user.get().getUserId(),
                user.get().getUserName(),
                user.get().getFollowers().size());
    }

    @Override
    public ResponseEntity<FollowersListResponseDTO> followersList(Integer sellerId, String order) {
        User user = userRepo.findById(sellerId).get();
        List<User> followers = user.getFollowers();
        if (order != null)
            followers.sort( Sorter.nameSorter(order));

        FollowersListResponseDTO followersListResponseDTO =
                new FollowersListResponseDTO(user.getUserId(), user.getUserName(), followers);
        return new ResponseEntity<>(
                followersListResponseDTO,
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FollowedListResponseDTO> followedList(Integer userId, String order) {
        User user = userRepo.findById(userId).get();
        if (user == null)
            throw new UserDoesNotExist(userId);
        List<User> followed = user.getFollowed();
        followed.sort(new SortByNameAsc());
        FollowedListResponseDTO followedListResponseDTO =
                new FollowedListResponseDTO(user.getUserId(), user.getUserName(), followed);
        return new ResponseEntity<>(
                followedListResponseDTO,
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostResponseDTO> newPost(PostRequestDTO postRequestDTO) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(postRequestDTO.getDate());
            validate(date, postRequestDTO.getDate());
        } catch (Exception e) {
            throw new BadDateFormat(postRequestDTO.getDate());
        }
        Optional<User> user = userRepo.findById(postRequestDTO.getUserId());
        if (user.isEmpty())
            throw new UserDoesNotExist(postRequestDTO.getUserId());
        PostResponseDTO postResponseDTO =
                user.get().newPost(postRequestDTO, id_post.getAndAdd(1), product_id.getAndAdd(1));
        return new ResponseEntity<>(postResponseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostResponseDTO> newPromoPost(PromoPostRequestDTO promoPostRequestDTO) {
        return newPost(promoPostRequestDTO);
    }

    @Override
    public ResponseEntity<PromoPostCountResponseDTO> promoPostCount(Integer userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty())
            throw new UserDoesNotExist(userId);
        return new ResponseEntity<>(
                new PromoPostCountResponseDTO(userId,user.get().getUserName(),user.get().getPostResponseDTOS().size()),
                HttpStatus.OK);
    }

    private void validate(Date date, String dateString) {
        /* String object which represents date validation */
        List<String> dateSplit = Arrays.asList(dateString.split("-", 3));
        if (    dateSplit.size()!=3 || Integer.parseInt(dateSplit.get(0))<1 ||
                Integer.parseInt(dateSplit.get(0))>31 || Integer.parseInt(dateSplit.get(1))<1 ||
                Integer.parseInt(dateSplit.get(1))>12)
            throw new RuntimeException();

        /* Date object validation */
        Calendar cal = Calendar.getInstance();
        cal.set(1999, 7, 0, 0, 0,0);
        Date MELIFoundation = cal.getTime();
        if (date.after(Calendar.getInstance().getTime()) || date.before(MELIFoundation))
            throw new RuntimeException();
    }

    @Override
    public ResponseEntity<PostsListResponseDTO> lastFollowedPosts(Integer userId, String order) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty())
            throw new UserDoesNotExist(userId);
        List<PostResponseDTO> posts = user.get().getFollowedLastPostedItems();
        if (posts.isEmpty())
            throw new NoPostsAvailableException(userId);
        if (order != null)
            posts.sort(Sorter.dateSorter(order));
        return new ResponseEntity<>(new PostsListResponseDTO(userId, posts), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostsListResponseDTO> promoPostsList(Integer userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty())
            throw new UserDoesNotExist(userId);
        List<PostResponseDTO> posts = user.get().getPostResponseDTOS()
                .stream()
                .filter(PostResponseDTO::hasPromo)
                .collect(Collectors.toList());
        if (posts.isEmpty())
            throw new NoPostsAvailableException(userId);
        return new ResponseEntity<>(new PostsListResponseDTO(userId, posts), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<SimpleUserDTO>> initialize() {
        return new ResponseEntity<>( getSimpleUsers(userRepo.initialize()), HttpStatus.OK);
    }

    public ResponseEntity<List<SimpleUserDTO>> getUsers() {
        return new ResponseEntity<>( getSimpleUsers(userRepo.getUsers()), HttpStatus.OK);
    }

    private List<SimpleUserDTO> getSimpleUsers(List<User> users) {
        List<SimpleUserDTO> simpleUsers = new ArrayList<>();
        for (User u : users) {
            simpleUsers.add(u.toSimpleUser());
        }
        return simpleUsers;
    }
}
