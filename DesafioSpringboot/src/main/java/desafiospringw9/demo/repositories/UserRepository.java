package desafiospringw9.demo.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import desafiospringw9.demo.exceptions.RelationNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;
import desafiospringw9.demo.models.UserModel;
import desafiospringw9.demo.models.UserRelation;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {


    private List<UserModel> users = loadUserDB();
    private List<UserRelation> userRelations = loadRelationDB();
    private int counterRelations = userRelations.size();
    private int counterUsers = users.size();

    @Override
    public void addFollower(int follower, int following) throws UserNotValidException, RelationNotValidException {

        boolean followerIsValid = validateUser(follower);
        boolean followingIsValid = validateUser(following);

        validateRelation(follower, following);

        if (followerIsValid && followingIsValid) {
            counterRelations++;
            userRelations.add(new UserRelation(counterRelations, follower, following));
        }
    }

    @Override
    public int removeFollower(int userId, int userIdToUnfollow){
        int relationId = -1;
        for(UserRelation r:userRelations){
            if(r.getFollower() == userId && r.getFollowing()==userIdToUnfollow){
                relationId = r.getRelationId();
                this.userRelations.remove(r);
                break;
            }
        }
        return relationId;
    }

    @Override
    public int countFollowers(int userId){
        int count = 0;
        for(UserRelation r:userRelations){
            if(r.getFollowing() == userId){
                count++;
            }
        }
        return count;
    }

    @Override
    public UserModel getUserById(int userId) throws UserNotValidException {
        for(UserModel u:users){
            if(u.getUserId() == userId){
                return u;
            }
        }
        throw new UserNotValidException(userId);
    }


    public boolean validateUser(int userId) throws UserNotValidException {
        for (UserModel u : users) {
            if (u.getUserId() == userId) {
                return true;
            }
        }
        throw new UserNotValidException(userId);
    }

    public void validateRelation(int followerId, int followingId) throws RelationNotValidException {
        if (followerId == followingId) {
            throw new RelationNotValidException(followerId, followingId);
        }
        for (UserRelation r : userRelations) {
            if (r.getFollower() == followerId && r.getFollowing() == followingId) {
                throw new RelationNotValidException(followerId, followingId);
            }

        }
    }

    //0003
    @Override
    public List<UserModel> getFollowers(int userId){
        List<UserModel> followers = new ArrayList<>();
        for(UserRelation r:userRelations){
            if(r.getFollowing() == userId){
                try{
                    followers.add(this.getUserById(r.getFollower()));
                }catch (UserNotValidException e) {
                    e.printStackTrace();
                }
            }
        }
        return followers;
    }

    //0004
    @Override
    public List<UserModel> getFollowed(int userId){
        List<UserModel> followed = new ArrayList<>();
        for(UserRelation r:userRelations){
            if(r.getFollower()==userId){
                try{
                    followed.add(this.getUserById(r.getFollowing()));
                }catch (UserNotValidException e){
                    e.printStackTrace();
                }
            }
        }
        return followed;
    }



    //conecto a mi base de datos
    private List<UserModel> loadUserDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserModel>> typeRef = new TypeReference<>() {
        };

        List<UserModel> db = null;

        try {
            db = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }

    private List<UserRelation> loadRelationDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:relations.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserRelation>> typeRef = new TypeReference<>() {
        };

        List<UserRelation> db = null;

        try {
            db = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }
}




