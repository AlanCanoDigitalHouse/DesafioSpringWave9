package com.meli.itbootcamp.repositories;

import com.meli.itbootcamp.handlers.DataLoader;
import com.meli.itbootcamp.model.User;
import com.meli.itbootcamp.model.UserNonSeller;
import com.meli.itbootcamp.model.UserSeller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements  UserRepository{
    private  final static  List<UserNonSeller> usersMeli = new ArrayList<>();
    private  final static  List<UserSeller> sellersMeli = new ArrayList<>();

    public UserRepositoryImpl() {
        DataLoader.loadUserSeller().forEach(seller-> sellersMeli.add(new UserSeller(seller.getUserId(),
                seller.getUserName())));
        DataLoader.loadNonSeller().forEach(nonSeller-> usersMeli.add(new UserNonSeller(nonSeller.getUserId(),
                nonSeller.getUserName())));
    }

    @Override
    public UserNonSeller saveUser(UserNonSeller toSave) {

        if(Objects.isNull(toSave.getUserID())){
            toSave.setUserID(usersMeli.size()+1);
            usersMeli.add(toSave);
        }
        return toSave;
    }

    @Override
    public UserSeller saveUser(UserSeller toSave) {

        if(Objects.isNull(toSave.getUserID())){
            toSave.setUserID(sellersMeli.size()+1);
            sellersMeli.add(toSave);
        }
        return toSave;
    }

    @Override
    public List<User> findAll() {
        List<User> allUser =new ArrayList<>();
        allUser.addAll(usersMeli);
        allUser.addAll(sellersMeli);
        return allUser;
    }

    @Override
    public UserSeller findUserSellerById(Integer userId) {
        Optional<UserSeller> result =sellersMeli.stream().filter(user -> user.getUserID().equals(userId)).findFirst();
        if(result.isPresent()){
            return  result.get();
        }
        else
            return null;
    }

    @Override
    public UserNonSeller findUserNonSellerById(Integer userId) {
        Optional<UserNonSeller> result =usersMeli.stream().filter(user -> user.getUserID().equals(userId)).findFirst();
        if(result.isPresent()){
            return  result.get();
        }
        else
            return null;
    }

    @Override
    public User findUserByName(String userName) {
//        Optional<User> result =usersMeli.stream().filter(user -> user.getUserName().equals(userName)).findFirst();
//        if(result.isPresent()){
//            return  result.get();
//        }
//        else
            return null;
    }

}
