package desafiospringw9.demo.services;

import desafiospringw9.demo.dtos.FollowListDTO;
import desafiospringw9.demo.dtos.FollowersCountDTO;
import desafiospringw9.demo.exceptions.RelationNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;

public interface IUserService {

    void addFollower(int follower, int following) throws UserNotValidException, RelationNotValidException;

    FollowersCountDTO countFollowers(int userId) throws UserNotValidException;

    FollowListDTO getFollowers(int userId) throws UserNotValidException;


}
