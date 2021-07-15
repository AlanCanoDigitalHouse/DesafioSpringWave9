package desafiospringw9.demo.services;

import desafiospringw9.demo.dtos.FollowListDTO;
import desafiospringw9.demo.dtos.FollowersCountDTO;
import desafiospringw9.demo.exceptions.RelationNonExistentException;
import desafiospringw9.demo.exceptions.RelationNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;

public interface IUserService {

    void addFollower(int follower, int following) throws UserNotValidException, RelationNotValidException;

    FollowersCountDTO countFollowers(int userId) throws UserNotValidException;

    FollowListDTO getFollowers(int userId, String order) throws UserNotValidException;

    FollowListDTO getFollowed(int userId) throws UserNotValidException;

    String removeFollower(int userId, int userIdToUnfollow) throws RelationNotValidException, RelationNonExistentException;

}
