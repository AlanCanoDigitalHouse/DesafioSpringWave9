package desafiospringw9.demo.services;


import desafiospringw9.demo.dtos.FollowListDTO;
import desafiospringw9.demo.dtos.FollowersCountDTO;
import desafiospringw9.demo.exceptions.RelationNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;
import desafiospringw9.demo.models.UserModel;
import desafiospringw9.demo.repositories.IUserRepository;
import desafiospringw9.demo.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public void addFollower(int follower, int following)throws UserNotValidException, RelationNotValidException {
        userRepository.addFollower(follower, following);
    }

    @Override
    public FollowersCountDTO countFollowers(int userId) throws UserNotValidException{

        String userName = userRepository.getUserById(userId).getUserName();
        int followers = userRepository.countFollowers(userId);


        return new FollowersCountDTO(userId, userName, followers);

    }

    @Override
    public FollowListDTO getFollowers(int userId) throws UserNotValidException{

        String userName = userRepository.getUserById(userId).getUserName();
        List<UserModel> followers = userRepository.getFollowers(userId);


        FollowListDTO response = new FollowListDTO();
        response.setUserId(userId);
        response.setUserName(userName);
        response.setRelated(UserMapper.toDTO(followers));

        return response;


    }

}
