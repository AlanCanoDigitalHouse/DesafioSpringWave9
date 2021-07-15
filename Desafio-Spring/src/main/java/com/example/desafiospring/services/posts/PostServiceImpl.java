package com.example.desafiospring.services.posts;

import com.example.desafiospring.dtos.Post;
import com.example.desafiospring.dtos.User;
import com.example.desafiospring.dtos.UserPosts;
import com.example.desafiospring.exceptions.*;
import com.example.desafiospring.repositories.SocialMediaRepository;
import com.example.desafiospring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    SocialMediaRepository socialMediaRepository;

    @Autowired
    UserService userService;

    @Override
    public void createPost(Post post) throws UserNotExistException {
        validateUser(post.getUserId());
        if(post.getHasPromo()==null){
            post.setHasPromo(null);
            post.setDiscount(null);
        }
        socialMediaRepository.savePost(post);
    }

    @Override
    public void createPromoPost(Post post)
            throws UserNotExistException, NeedDiscountException, HasPromoException, DontNeedDiscountException {
        validateUser(post.getUserId());
        if(post.getHasPromo()==null) {
            throw new HasPromoException();
        }

        if(post.getHasPromo()&&post.getDiscount()==null) {
            throw new NeedDiscountException();
        }

        if(!post.getHasPromo()&&post.getDiscount()!=null) {
            throw new DontNeedDiscountException();
        }

        socialMediaRepository.savePost(post);
    }

    @Override
    public UserPosts findPostsByUserId (Integer userId, String order)
            throws UserNotExistException, UserDontHaveFollowersorFollowed, UserDontHavePostsException {
        validateUser(userId);
        List<Integer> listIdsFollowed = getFollowedList(userId);

        if(listIdsFollowed.isEmpty()) {
            throw new UserDontHaveFollowersorFollowed("followed");
        }
        UserPosts userPosts = new UserPosts(
                userId,null, null, socialMediaRepository.findPostsByUserIds(listIdsFollowed));
        LocalDate maxDate = LocalDate.now().plusDays(1);
        LocalDate minDate = maxDate.minusWeeks(2).minusDays(1);
        userPosts.setPosts(userPosts.getPosts()
                .stream()
                .filter(post -> post.getDate().isAfter(minDate)&& post.getDate().isBefore(maxDate))
                .collect(Collectors.toList()));
        userPosts.getPosts().sort(Comparator.comparing(Post::getDate).reversed());
        if(userPosts.getPosts().isEmpty()) {
            throw new UserDontHavePostsException();
        }

        if(!order.isEmpty()) {
            return getOrderedPosts(userPosts, order);
        }

        return userPosts;
    }

    @Override
    public UserPosts countPromo (Integer userId) throws UserNotExistException, UserDontHavePostsException {
        Optional<User> user = socialMediaRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotExistException();
        }
        UserPosts userPosts = new UserPosts(userId,null ,null , socialMediaRepository.findPostsByUserId(userId));

        userPosts.setPosts(userPosts.getPosts().stream()
                .filter(post -> post.getHasPromo()!=null)
                .filter(Post::getHasPromo).collect(Collectors.toList()));

        return new UserPosts(userId,user.get().getUserName(), userPosts.getPosts().size(), null);
    }

    @Override
    public UserPosts getPosts (Integer userId) throws UserDontHavePostsException, UserNotExistException, NotHavePromoProducts {
        validateUser(userId);
        UserPosts userPosts = new UserPosts(
                userId,null ,null , socialMediaRepository.findPostsByUserId(userId));
        userPosts.setPosts(userPosts.getPosts().stream()
                .filter(post -> post.getHasPromo()!=null)
                .filter(Post::getHasPromo).collect(Collectors.toList()));
        if(userPosts.getPosts().isEmpty()) {
            throw new NotHavePromoProducts();
        }
        return userPosts;
    }


    @Override
    public UserPosts getOrderedPosts (UserPosts userPosts, String order) {
        if(order.equals("date_asc")) {
            userPosts.getPosts().sort(Comparator.comparing(Post::getDate));
        } else if (order.equals("date_dsc")) {
            userPosts.getPosts().sort(Comparator.comparing(Post::getDate).reversed());
        }
        return userPosts;
    }

    private void validateUser (Integer userId) throws UserNotExistException {
        Optional<User> user = socialMediaRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotExistException();
        }
    }

    private List<Integer> getFollowedList(Integer userId)
            throws UserNotExistException, UserDontHaveFollowersorFollowed {
        User user = userService.followedList(userId, "");
        List<Integer> followedIds = new ArrayList<>();
        List<User> userList = user.getFollowed();
        for (User userItem: userList) {
              followedIds.add(userItem.getUserId());
        }
        return followedIds;
    }
}
