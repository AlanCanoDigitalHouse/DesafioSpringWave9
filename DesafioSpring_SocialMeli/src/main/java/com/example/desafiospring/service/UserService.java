package com.example.desafiospring.service;


import com.example.desafiospring.dtos.*;
import com.example.desafiospring.dtos.request.UserRequestDTO;
import com.example.desafiospring.dtos.response.PostResponseDTO;
import com.example.desafiospring.dtos.response.PostsUserResponseDTO;
import com.example.desafiospring.dtos.response.UserResponseDTO;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.UserIdEqualUserToFollowException;
import com.example.desafiospring.exceptions.UserNotExistsException;
import com.example.desafiospring.exceptions.UserNotFollowedException;
import com.example.desafiospring.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IPostService postService;


    //metodos publicos
    @Override
    public List<UserRequestDTO> getUsers() {
        return userRepository.getUsersDB();
    }


    @Override
    public void follow(int pUserId, int pUserIdToFollow) throws UserIdEqualUserToFollowException, UserNotExistsException, AlreadyFollowedException {

        if (pUserId == pUserIdToFollow)
            throw new UserIdEqualUserToFollowException("Ambos userID son iguales:  " + pUserId);

        if (!userRepository.userExsistsDB(pUserId))
            throw new UserNotExistsException("El userID " + pUserId + " no existe.");

        if (!userRepository.userExsistsDB(pUserIdToFollow))
            throw new UserNotExistsException("El userID a seguir " + pUserIdToFollow + " no existe.");


        UserRequestDTO vUser = userRepository.getUserDB(pUserId);
        UserRequestDTO vUserToFollow = userRepository.getUserDB(pUserIdToFollow);

        followUser(vUser, new FollowDTO(vUserToFollow.getUserId(), vUserToFollow.getUserName()));
        updateFollowersOfFollowed(vUserToFollow, new FollowDTO(vUser.getUserId(), vUser.getUserName()), true);

    }


    @Override
    public void unfollow(int pUserId, int userIdToUnfollow) throws UserIdEqualUserToFollowException, UserNotExistsException, UserNotFollowedException {
        if (pUserId == userIdToUnfollow)
            throw new UserIdEqualUserToFollowException("Los userID no pueden ser iguales: " + pUserId);

        if (!userRepository.userExsistsDB(pUserId))
            throw new UserNotExistsException("El userID " + pUserId + " no existe.");

        if (!userRepository.userExsistsDB(userIdToUnfollow))
            throw new UserNotExistsException("El userID a seguir " + userIdToUnfollow + " no existe.");

        UserRequestDTO vUser = userRepository.getUserDB(pUserId);
        FollowDTO vFollow = userRepository.getUserFollowedDB(vUser, userIdToUnfollow);

        unfollowUser(vUser, vFollow);
        updateFollowersOfFollowed(vUser, vFollow, false);

    }


    @Override
    public UserRequestDTO getUserFollowers(int pUserId, String pOrder) throws UserNotExistsException {

        if (!userRepository.userExsistsDB(pUserId))
            throw new UserNotExistsException("El userID " + pUserId + " no existe.");

        UserRequestDTO vUserDTO = userRepository.getUserDB(pUserId);

        if (pOrder.equals("name_asc"))
            vUserDTO.getFollowers().sort(Comparator.comparing(FollowDTO::getUserName));
        else if (pOrder.equals("name_desc"))
            vUserDTO.getFollowers().sort(Comparator.comparing(FollowDTO::getUserName).reversed());

        return new UserRequestDTO(pUserId, vUserDTO.getUserName(), null, vUserDTO.getFollowers());

    }


    @Override
    public UserRequestDTO getUserFollowed(int pUserId, String pOrder) throws UserNotExistsException {

        if (!userRepository.userExsistsDB(pUserId))
            throw new UserNotExistsException("El userID " + pUserId + " no existe.");

        UserRequestDTO vUserDTO = userRepository.getUserDB(pUserId);

        if (pOrder.equals("name_asc"))
            vUserDTO.getUsersFollowed().sort(Comparator.comparing(FollowDTO::getUserName));
        else if (pOrder.equals("name_desc"))
            vUserDTO.getUsersFollowed().sort(Comparator.comparing(FollowDTO::getUserName).reversed());

        return new UserRequestDTO(pUserId, vUserDTO.getUserName(), vUserDTO.getUsersFollowed(), null);

    }

    @Override
    public PostsUserResponseDTO getPostsByUser(int pUserID, String pOrder) throws UserNotExistsException {

        if (!userRepository.userExsistsDB(pUserID))
            throw new UserNotExistsException("El userID " + pUserID + " no existe.");

        PostsUserResponseDTO vPostsUserResponseDTO = new PostsUserResponseDTO(pUserID);
        List<PostResponseDTO> vPostList = new ArrayList<>();

        UserRequestDTO vUser = userRepository.getUserDB(pUserID);
        List<FollowDTO> vListUsersFollowed = vUser.getUsersFollowed();

        for (FollowDTO followDTO : vListUsersFollowed
        ) {
            for (PostResponseDTO vPost : postService.getPostsByUser(followDTO)
            ) {
                vPostList.add(vPost);
            }
        }

        if (pOrder.equals("name_asc"))
            vPostList.sort(Comparator.comparing(PostResponseDTO::getDate));
        else if (pOrder.equals("name_desc"))
            vPostList.sort(Comparator.comparing(PostResponseDTO::getDate).reversed());

        vPostsUserResponseDTO.setPosts(vPostList);
        return vPostsUserResponseDTO;
    }


    @Override
    public UserResponseDTO getUserWithFollowersQuantity(int pUserId) throws UserNotExistsException {

        if (!userRepository.userExsistsDB(pUserId))
            throw new UserNotExistsException("El userID " + pUserId + " no existe.");

        UserRequestDTO vUser = userRepository.getUserDB(pUserId);

        return new UserResponseDTO(
                pUserId,
                vUser.getUserName(),
                getFollowersQuantity(vUser)
        );

    }


    //metodos privados
    private void followUser(UserRequestDTO pUserId, FollowDTO pUserIdToFollow) throws AlreadyFollowedException {

        if (userAlreadyFollowed(pUserId, pUserIdToFollow.getUserId()))
            throw new AlreadyFollowedException("El userID " + pUserId.getUserId() +
                    " ya sigue al userID " + pUserIdToFollow.getUserId());

        ArrayList<FollowDTO> vFollowed = pUserId.getUsersFollowed();
        vFollowed.add(pUserIdToFollow);
    }

    private void unfollowUser(UserRequestDTO pUserId, FollowDTO pUserIdToFollow) throws UserNotFollowedException {

        if (!userAlreadyFollowed(pUserId, pUserIdToFollow.getUserId()))
            throw new UserNotFollowedException("El userID " + pUserId.getUserId() +
                    " no sigue al userID " + pUserIdToFollow.getUserId());

        ArrayList<FollowDTO> vFollowed = pUserId.getUsersFollowed();
        vFollowed.remove(pUserIdToFollow);
    }


    private void updateFollowersOfFollowed(UserRequestDTO pUser, FollowDTO pFollower, boolean pFollow) {

        ArrayList<FollowDTO> vFollowers;

        if (pFollow){
            vFollowers = pUser.getFollowers();
            vFollowers.add(pFollower);
        }
        else {
           UserRequestDTO vUser = userRepository.getUserDB(pFollower.getUserId());
           vFollowers = vUser.getFollowers();
           FollowDTO vFollow = userRepository.getUserFollowerDB(vUser, pUser.getUserId());
           vFollowers.remove(vFollow);
        }
    }


    private boolean userAlreadyFollowed(UserRequestDTO pUser, int pUserIdToFollow) {

        return pUser.getUsersFollowed().stream()
                .filter(user -> user.getUserId() == pUserIdToFollow)
                .findFirst().isEmpty() ? false : true;
    }


    private int getFollowersQuantity(UserRequestDTO pUserDTO) {
        return pUserDTO.getFollowers().size();
    }

}
