package com.example.desafiospring.services.user;


import com.example.desafiospring.dtos.User;
import com.example.desafiospring.exceptions.AlreadyFollow;
import com.example.desafiospring.exceptions.SameUserException;
import com.example.desafiospring.exceptions.UserDontHaveFollowersorFollowed;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.repositories.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    SocialMediaRepository socialMediaRepository;

    @Override
    public void follow(Integer userId, Integer userIdToFollow)
            throws UserNotExistException, SameUserException, AlreadyFollow {
        Optional<User> optionalBuyer = socialMediaRepository.findById(userId);
        if(optionalBuyer.isEmpty()) {
            throw new UserNotExistException();
        }

        Optional<User> optionalSeller = socialMediaRepository.findById(userIdToFollow);
        if(optionalSeller.isEmpty()) {
            throw new UserNotExistException();
        }

        if(optionalBuyer.get().getUserId().equals(optionalSeller.get().getUserId())) {
            throw new SameUserException();
        }

        if(optionalBuyer.get().getFollowed().contains(new User
                (optionalSeller.get().getUserId(),optionalSeller.get().getUserName(), null,null,null,null))) {
            throw new AlreadyFollow();
        }

        socialMediaRepository.saveFollowed(userId, optionalSeller.get());
        socialMediaRepository.saveFollowers(userIdToFollow, optionalBuyer.get());

    }

    @Override
    public User followersCount(Integer userId) throws UserNotExistException, UserDontHaveFollowersorFollowed {
        Optional<User> buyer = socialMediaRepository.findById(userId);
        if(buyer.isEmpty()){
            throw new UserNotExistException();
        }

        if(buyer.get().getFollowers() == null || buyer.get().getFollowers().isEmpty()) {
            throw new UserDontHaveFollowersorFollowed("followers");
        }

        buyer.get().setFollowersCount(buyer.get().getFollowers().size());
        User user = buyer.get();

        return new User(user.getUserId(), user.getUserName()
                ,null,null,null,user.getFollowersCount());
    }

    @Override
    public User followersList(Integer userId, String order) throws UserNotExistException, UserDontHaveFollowersorFollowed {
        Optional<User> seller = socialMediaRepository.findById(userId);
        if(seller.isEmpty()){
            throw new UserNotExistException();
        }

        if(seller.get().getFollowers() == null || seller.get().getFollowers().isEmpty()) {
            throw new UserDontHaveFollowersorFollowed("followers");
        }

        User user = seller.get();
        orderListFollowers(user, order);
        return new User(user.getUserId(), user.getUserName()
                ,null,user.getFollowers(),null,null);
    }

    @Override
    public User followedList(Integer userId, String order) throws UserNotExistException, UserDontHaveFollowersorFollowed {
        Optional<User> buyer = socialMediaRepository.findById(userId);
        if(buyer.isEmpty()){
            throw new UserNotExistException();
        }
        if(buyer.get().getFollowed() == null || buyer.get().getFollowed().isEmpty()) {
            throw new UserDontHaveFollowersorFollowed("followed");
        }

        User user = buyer.get();
        orderListFollowed(user, order);
        return new User(user.getUserId(), user.getUserName()
                ,user.getFollowed(),null,null,null);
    }

    @Override
    public void unFollow(int userId, int userIdToUnfollow) throws UserNotExistException, SameUserException {

        Optional<User> optionalBuyer = socialMediaRepository.findById(userId);
        validateUser(optionalBuyer);

        Optional<User> optionalSeller = socialMediaRepository.findById(userIdToUnfollow);
        validateUser(optionalSeller);

        if(optionalBuyer.get().getUserId().equals(optionalSeller.get().getUserId())) {
            throw new SameUserException();
        }

        socialMediaRepository.removeFollowed(userId, optionalSeller.get());
        socialMediaRepository.removeFollower(userIdToUnfollow, optionalBuyer.get());
    }

    @Override
    public void orderListFollowers(User user, String order)  {
        if(order.equals("name_asc")) {
            user.getFollowers().sort(Comparator.comparing(User::getUserName));
        } else if (order.equals("name_dsc")) {
            user.getFollowers().sort(Comparator.comparing(User::getUserName).reversed());
        }
    }

    @Override
    public void orderListFollowed(User user, String order) throws UserNotExistException {
        if(order.equals("name_asc")) {
            user.getFollowed().sort(Comparator.comparing(User::getUserName));
        } else if (order.equals("name_dsc")) {
            user.getFollowed().sort(Comparator.comparing(User::getUserName).reversed());
        }
    }


    private void validateUser (Optional<User> user) throws UserNotExistException {
        if(user.isEmpty()) {
            throw new UserNotExistException();
        }
    }

}
