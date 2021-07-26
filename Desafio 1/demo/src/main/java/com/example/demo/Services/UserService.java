package com.example.demo.Services;

import com.example.demo.DTOs.Request.PostRequestDTO;
import com.example.demo.Entities.Post;

import com.example.demo.Entities.User;
import com.example.demo.Repositiories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    public UserService(){
        this.userRepository=UserRepository.getInstance();
    }

    public static final AtomicInteger countPost = new AtomicInteger(1);

    //1
    @Override
    public boolean followAuser(Integer userId, Integer userToFollow){
        User user=userRepository.findById(userId);
        User user2=userRepository.findById(userToFollow);

        if (user != null && user2 != null && !userId.equals(userToFollow) && user.getUserFollow().get(userToFollow) == null && user2.getUserFollowers().get(userId) == null)   {
            user.getUserFollow().put(user2.getUserid(),user2);
            user2.getUserFollowers().put(user.getUserid(),user);
            return true;
        }
        return  false;
    }

    ///3
    @Override
    public List<User> followersOf (Integer userId){
        User user=userRepository.findById(userId);

        if (user!= null ){
            return new ArrayList<>(user.getUserFollowers().values());
        }
        return null;
    }

    ///2
    @Override
    public int followerSize(Integer userId){
        User user=userRepository.findById(userId);

        if (user!= null ){
            return user.getUserFollowers().size();
        }

        return -1;
    }


    //4
    @Override
    public List<User> followedByUserByID(Integer userId){
        User user=userRepository.findById(userId);

        if (user!= null ){
            return new ArrayList<>(user.getUserFollow().values());
        }
        return null;
    }

    ///5
    @Override
    public boolean newPost (PostRequestDTO postRequestDTO) {
        User user=userRepository.findById(postRequestDTO.getUserId());

        if (user != null){
            Date date=new Date();
            Post aPost = new Post(countPost.getAndIncrement(),postRequestDTO.getUserId(),date,postRequestDTO.getDetail(),postRequestDTO.getPrice(),postRequestDTO.getCategory());

            if (user.getUserPost().get(aPost.getPostId()) == null) {
                user.getUserPost().put(aPost.getPostId(), aPost);
                return true;
            }
        }
        return false;
    }

    ///6
    @Override
    public List<Post> postList (Integer userId){
        User user=userRepository.findById(userId);
        List<User> whoIFollow = new ArrayList<>(user.getUserFollow().values());
        List<Post> visibles = new ArrayList<>();
        Date limitDate = new Date(System.currentTimeMillis() -( 14 * 24 * 3600 * 1000));

        for (User users: whoIFollow) {
            for (Post post: new ArrayList<>(users.getUserPost().values())){
                if (post.getDate().compareTo(limitDate)>0){
                    visibles.add(post);
                }
            }
        }
        return visibles;
    }

    ///7
    @Override
    public boolean unfollowAuser(Integer userId, Integer userToFollow){
        User user=userRepository.findById(userId);
        User user2=userRepository.findById(userToFollow);

        if (user != null && user2 != null && !userId.equals(userToFollow))   {
            user.getUserFollow().remove(user2.getUserid());
            user2.getUserFollowers().remove(user.getUserid());
            return true;
        }
        return  false;
    }

}
