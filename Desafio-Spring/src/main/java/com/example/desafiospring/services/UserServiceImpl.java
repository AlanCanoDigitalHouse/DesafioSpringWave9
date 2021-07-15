package com.example.desafiospring.services;

import com.example.desafiospring.dtos.response.UserDTO;
import com.example.desafiospring.dtos.general.UserInfo;
import com.example.desafiospring.dtos.response.FollowedsListDTO;
import com.example.desafiospring.dtos.response.FollowersCountDTO;
import com.example.desafiospring.dtos.response.FollowersListDTO;
import com.example.desafiospring.exceptions.UserNotFindOrEqualException;
import com.example.desafiospring.exceptions.UserNotFollowToUserException;
import com.example.desafiospring.exceptions.UserToFollowAlreadyExistException;
import com.example.desafiospring.repositories.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    private final UserRepositoryImpl userRepository;

    public UserServiceImpl(UserRepositoryImpl repository) {
        this.userRepository = repository;
    }

    @Override
    public ResponseEntity<?> newFollow(Integer userId, Integer userToFollow) throws UserNotFindOrEqualException, UserToFollowAlreadyExistException {
        UserInfo user = userRepository.getUser(userId);
        UserInfo userTo = userRepository.getUser(userToFollow);
        if((user != null && userTo !=null ) && user != userTo ) {
            UserDTO userAux = new UserDTO(userId, user.getUserName());
            UserDTO userAuxToFollow = new UserDTO(userToFollow, userTo.getUserName());
            if (existInList(userAuxToFollow, user.getFollower())) {
                throw new UserToFollowAlreadyExistException();
            }else{
                user.getFollower().add(userAuxToFollow);
                userTo.getFollowed().add(userAux);
            }
            userRepository.updateUsersFile();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            throw  new UserNotFindOrEqualException();
        }
    }

    @Override
    public FollowersCountDTO followerCount(Integer userId) throws UserNotFindOrEqualException {
        FollowersCountDTO response = new FollowersCountDTO();
        UserInfo user = userRepository.getUser(userId);
        if (user != null){
            response.setUserId(userId);
            response.setUserName(user.getUserName());
            response.setFollowers_count(user.getFollower().size());
            return response;
        }else{
            throw new UserNotFindOrEqualException();
        }
    }

    @Override
    public FollowersListDTO followerList(Integer userId, String mode) throws UserNotFindOrEqualException {
        FollowersListDTO response = new FollowersListDTO();
        UserInfo user = userRepository.getUser(userId);
        if (user != null){
            response.setUserId(userId);
            response.setUserName(user.getUserName());
            response.setFollowers(sortedList(mode, user.getFollower()));
            return response;
        }else{
            throw new UserNotFindOrEqualException();
        }
    }

    @Override
    public FollowedsListDTO followedList(Integer userId, String mode) throws UserNotFindOrEqualException {
        FollowedsListDTO response = new FollowedsListDTO();
        UserInfo user = userRepository.getUser(userId);
        if (user != null){
            response.setUserId(userId);
            response.setUserName(user.getUserName());
            response.setFolloweds(sortedList(mode, user.getFollowed()));
            return response;
        }else{
            throw new UserNotFindOrEqualException();
        }
    }

    @Override
    public ResponseEntity<?> unfollowUser(Integer userId, Integer userToUnfollow) throws UserNotFindOrEqualException, UserNotFollowToUserException {
        UserInfo user = userRepository.getUser(userId);
        UserInfo userTo = userRepository.getUser(userToUnfollow);
        if(user != null && userTo !=null) {
            UserDTO userAux = new UserDTO(userId, user.getUserName());
            UserDTO userAuxToUnfollow = new UserDTO(userToUnfollow, userTo.getUserName());
            if (existInList(userAuxToUnfollow, user.getFollower()) ) {
                user.getFollower().remove(userAuxToUnfollow);
                userTo.getFollowed().remove(userAux);
            }else{
                throw new UserNotFollowToUserException();
            }
            userRepository.updateUsersFile();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            throw  new UserNotFindOrEqualException();
        }
    }

    private boolean existInList(Object objectToFind, List<?> list){
        boolean status = false;
        Object aux = list.stream()
                .filter(element -> objectToFind.equals(element))
                .findAny()
                .orElse(null);
        if(aux!=null){
            status = true;
        }
        return status;
    }

    private List<UserDTO> sortedList(String mode, List<UserDTO> userList){
        List<UserDTO> aux = new ArrayList<>(userList);
        if(mode.compareTo("name_asc") == 0){
            Comparator<UserDTO> mapComparatorASC = (UserDTO m1, UserDTO m2) -> m1.getUserName().compareTo(m2.getUserName());
            Collections.sort(aux, mapComparatorASC);
        }
        if(mode.compareTo("name_desc") == 0){
            Comparator<UserDTO> mapComparatorDESC = (UserDTO m1, UserDTO m2) -> m2.getUserName().compareTo(m1.getUserName());
            Collections.sort(aux, mapComparatorDESC);
        }
        if(mode.compareTo("default") == 0){
            System.out.println("no followers sorted");
        }
        return aux;
    }

    public UserInfo getUserFromRepository(Integer userId){
        return userRepository.getUser(userId);
    }

    @Override
    public List<String> getAllUsers() {
        List<String> aux = new ArrayList<>();
        for ( Integer key : userRepository.getKey() ) {
            UserInfo user = userRepository.getUser(key);
            aux.add(key.toString()+" "+user.getUserName());
        }
        return aux;
    }
}
