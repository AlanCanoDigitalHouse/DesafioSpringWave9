package com.mercadolibre.socialmeli.services.implementations;

import com.mercadolibre.socialmeli.dtos.users.VendorFollowQtyRespDTO;
import com.mercadolibre.socialmeli.dtos.users.UserFollowsRespDTO;
import com.mercadolibre.socialmeli.dtos.users.UserDTO;
import com.mercadolibre.socialmeli.exceptions.users.SameUserTwiceException;
import com.mercadolibre.socialmeli.exceptions.users.UserAlreadyFollowException;
import com.mercadolibre.socialmeli.exceptions.users.UserAlreadyUnfollowException;
import com.mercadolibre.socialmeli.exceptions.users.UserNotFoundException;
import com.mercadolibre.socialmeli.repositories.interfaces.IUserRepository;
import com.mercadolibre.socialmeli.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service("userServ")
public class UserServiceImpl implements IUserService {

    private final IUserRepository iUserRepository;

    public UserServiceImpl(@Qualifier("userRepo") IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public String followVendor(Integer userId, Integer userIdToFollow) throws UserAlreadyFollowException, SameUserTwiceException, UserNotFoundException, UserAlreadyUnfollowException {
        checkNotSameUser(userId,userIdToFollow);
        UserFollowsRespDTO user = checkUserExistence(userId);
        UserFollowsRespDTO userToFollow = checkUserExistence(userIdToFollow);

        updateFollows(user, userToFollow, true);
        return null;
    }

    /**
     *
     * @param userFull The user which will follow or unfollow
     * @param vendorFull The user which will be followed or unfollowed
     * @throws UserAlreadyFollowException
     */
    private void updateFollows(UserFollowsRespDTO userFull, UserFollowsRespDTO vendorFull, boolean addFollow)
            throws UserAlreadyFollowException, UserAlreadyUnfollowException {
        UserDTO vendorSimple = new UserDTO(vendorFull.getUserId(), vendorFull.getUserName());
        UserDTO userSimple = new UserDTO(userFull.getUserId(), userFull.getUserName());

        if (addFollow){
            if (canAddFollowed(userFull, vendorSimple)) {
                userFull.getFollowed().add(vendorSimple);
            } else {
                throw new UserAlreadyFollowException(vendorSimple.getUserName(), userSimple.getUserName());
            }
            if (canAddFollower(vendorFull, userSimple)) {
                vendorFull.getFollowers().add(userSimple);
            } else {
                throw new UserAlreadyFollowException(vendorSimple.getUserName(), userSimple.getUserName());
            }
        } else {
            if (canRemoveFollowed(userFull, vendorSimple)) {
                userFull.getFollowed().removeIf(user -> user.getUserId().equals(vendorSimple.getUserId()));
            } else {
                throw new UserAlreadyUnfollowException(vendorSimple.getUserName(), userSimple.getUserName());
            }
            if (canRemoveFollower(vendorFull, userSimple)) {
                vendorFull.getFollowers().removeIf(user -> user.getUserId().equals(userSimple.getUserId()));
            } else {
                throw new UserAlreadyUnfollowException(vendorSimple.getUserName(), userSimple.getUserName());
            }
        }
    }

    private boolean canAddFollowed(UserFollowsRespDTO userFull, UserDTO userSimple) {
        return userFull.getFollowed().stream().noneMatch(user -> user.getUserId().equals(userSimple.getUserId()));
    }

    private boolean canAddFollower(UserFollowsRespDTO userFull, UserDTO vendorSimple) {
        return userFull.getFollowers().stream().noneMatch(user -> user.getUserId().equals(vendorSimple.getUserId()));
    }

    private boolean canRemoveFollowed(UserFollowsRespDTO userFull, UserDTO userSimple) {
        return userFull.getFollowed().stream().anyMatch(user -> user.getUserId().equals(userSimple.getUserId()));
    }

    private boolean canRemoveFollower(UserFollowsRespDTO userFull, UserDTO vendorSimple) {
        return userFull.getFollowers().stream().anyMatch(user -> user.getUserId().equals(vendorSimple.getUserId()));
    }

    @Override
    public VendorFollowQtyRespDTO countVendorFollows(Integer userId) throws UserNotFoundException {
        UserFollowsRespDTO user = this.iUserRepository.find(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return new VendorFollowQtyRespDTO(userId, user.getUserName(), this.countFollowsQty(user.getFollowers()));
    }

    private Integer countFollowsQty(List<UserDTO> followers) {
        return followers.size();
    }

    @Override
    public UserFollowsRespDTO allVendorFollowers(Integer userID,String order) throws UserNotFoundException {
        UserFollowsRespDTO user = this.iUserRepository.find(userID);
        if (user == null) {
            throw new UserNotFoundException(userID);
        }
        List<UserDTO> listSorted = sortListBy(user.getFollowers(),order);
        return new UserFollowsRespDTO(user.getUserId(),user.getUserName(),listSorted,null);
    }

    private List<UserDTO> sortListBy(List<UserDTO> listUsers, String order) {
        if (order.equalsIgnoreCase("name_asc")){
            listUsers.sort(Comparator.comparing(UserDTO::getUserName));
        } else if (order.equalsIgnoreCase("name_desc")){
            listUsers.sort(Comparator.comparing(UserDTO::getUserName).reversed());
        }
        return listUsers;
    }

    @Override
    public UserFollowsRespDTO allUserFollowed(Integer userID,String order) throws UserNotFoundException {
        UserFollowsRespDTO user = this.iUserRepository.find(userID);
        if (user == null) {
            throw new UserNotFoundException(userID);
        }
        List<UserDTO> listSorted = sortListBy(user.getFollowed(),order);
        return new UserFollowsRespDTO(user.getUserId(),user.getUserName(),null,listSorted);
    }

    @Override
    public String unfollowVendor(Integer userId, Integer userIdToUnfollow) throws SameUserTwiceException, UserNotFoundException, UserAlreadyFollowException, UserAlreadyUnfollowException {
        checkNotSameUser(userId,userIdToUnfollow);
        UserFollowsRespDTO user = checkUserExistence(userId);
        UserFollowsRespDTO userToUnfollow = checkUserExistence(userIdToUnfollow);

        updateFollows(user,userToUnfollow,false);
        return null;
    }

    @Override
    public UserDTO findUserData(Integer userId) throws UserNotFoundException {
        UserFollowsRespDTO userSimpleData = this.iUserRepository.find(userId);
        if (Objects.isNull(userSimpleData)){
            throw new UserNotFoundException(userId);
        }
        return new UserDTO(userSimpleData.getUserId(), userSimpleData.getUserName());
    }

    private UserFollowsRespDTO checkUserExistence(Integer userId) throws UserNotFoundException, SameUserTwiceException {
        UserFollowsRespDTO user = this.iUserRepository.find(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return user;
    }

    private void checkNotSameUser(Integer userId, Integer userIdToUnfollow) throws SameUserTwiceException {
        if (userId.equals(userIdToUnfollow)) {
            throw new SameUserTwiceException(userId);
        }
    }



}
