package com.socialmeli.services.impl;

import com.socialmeli.dtos.ResponseDTO;
import com.socialmeli.dtos.UserDTO;
import com.socialmeli.dtos.request.SortEnum;
import com.socialmeli.dtos.response.CountFollowDTO;
import com.socialmeli.dtos.response.ListFollowedDTO;
import com.socialmeli.dtos.response.ListFollowerDTO;
import com.socialmeli.dtos.response.ResponseObjectDTO;
import com.socialmeli.models.User;
import com.socialmeli.models.UserSocial;
import com.socialmeli.repositories.ISocialRepository;
import com.socialmeli.services.IUsersService;
import com.socialmeli.utils.Comparators.ComparatorUserName;
import com.socialmeli.utils.Comparators.IComparator;
import com.socialmeli.utils.SortUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersService implements IUsersService {

    private final ISocialRepository socialRepository;

    public UsersService(ISocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }


    @Override
    public ResponseDTO followUser(Integer idUser, Integer idFollow) {
        UserSocial seller = socialRepository.findUserbyId(idFollow);
        UserSocial user = socialRepository.findUserbyId(idUser);
        if (!user.getFollowed().contains(seller)) {
            seller.getFollowers().add(user);
            user.getFollowed().add(seller);
        }
        return new ResponseDTO(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "User followed Successfully");

    }

    @Override
    public ResponseDTO unfollowUser(Integer idUser, Integer idUnFollow) {
        UserSocial seller = socialRepository.findUserbyId(idUnFollow);
        UserSocial user = socialRepository.findUserbyId(idUser);
        if (user.getFollowed().contains(seller)) {
            seller.getFollowers().remove(user);
            user.getFollowed().remove(seller);
        }
        return new ResponseDTO(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "User unfollowed Successfully");

    }

    @Override
    public ResponseDTO countFollowers(Integer idUser) {
        UserSocial user = socialRepository.findUserbyId(idUser);
        CountFollowDTO response = new CountFollowDTO(user.getId(), user.getName(), user.getFollowers().size());
        return new ResponseObjectDTO(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Count followers Successfully",
                response);
    }

    @Override
    public ResponseDTO listFollowers(Integer idUser, SortEnum sort) {
        UserSocial user = socialRepository.findUserbyId(idUser);
        ArrayList<UserDTO> followers = new ArrayList<>();
        for (User follower : user.getFollowers())
            followers.add(new UserDTO(follower.getId(), follower.getName()));

        //Sort followers by name
        orderUserByName(followers, sort);

        ListFollowerDTO response = new ListFollowerDTO(user.getId(), user.getName(), followers);
        return new ResponseObjectDTO(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Followers list obtained successfully",
                response);
    }

    @Override
    public ResponseDTO listFollowed(Integer idUser, SortEnum sort) {
        UserSocial user = socialRepository.findUserbyId(idUser);
        ArrayList<UserDTO> followed = new ArrayList<>();
        for (User follow : user.getFollowed())
            followed.add(new UserDTO(follow.getId(), follow.getName()));

        //Sort followers by name
        orderUserByName(followed, sort);
        ListFollowedDTO response = new ListFollowedDTO(user.getId(), user.getName(), followed);
        return new ResponseObjectDTO(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Followed list obtained successfully",
                response);
    }

    private void orderUserByName(ArrayList<UserDTO> users, SortEnum sort) {
        IComparator<UserDTO> comparator = new ComparatorUserName();
        if (sort.equals(SortEnum.name_asc))
            SortUtils.sortAsc(users, comparator);
        else if (sort.equals(SortEnum.name_desc))
            SortUtils.sortDesc(users, comparator);
    }
}
