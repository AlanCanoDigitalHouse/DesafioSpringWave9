package com.example.DesafioSpring.servicies;

import com.example.DesafioSpring.dtos.BuyerResponseDTO;
import com.example.DesafioSpring.dtos.BuyerResponseMsgDTO;
import com.example.DesafioSpring.dtos.SellerResponseDTO;
import com.example.DesafioSpring.dtos.SellerResponseSingleDTO;
import com.example.DesafioSpring.exceptions.UnableToFollowException;
import com.example.DesafioSpring.exceptions.UnrelatedUsersException;
import com.example.DesafioSpring.exceptions.UsersDoNotExistException;
import com.example.DesafioSpring.models.Buyer;
import com.example.DesafioSpring.models.Seller;
import com.example.DesafioSpring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service

public class UserService implements IUserService{

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // 1 - Follow a specific seller
    @Override
    public BuyerResponseMsgDTO followSeller(Integer userId, Integer userIdFollow) {
        var auxBuyer = userRepository.findUserById(userId);
        var auxSeller = userRepository.findUserById(userIdFollow);
        BuyerResponseMsgDTO responseDTO = new BuyerResponseMsgDTO();
        String aux;
        if (auxSeller & auxBuyer){
            Boolean isFollow = userRepository.isFollow(userId, userIdFollow);
            if (!isFollow){
                userRepository.addFollowLink(userId, userIdFollow);
                aux = "The buyer " + userId + " is now following the seller " + userIdFollow + "!";
                responseDTO.setMessage(aux);
                return responseDTO;
            }else {
                throw new UnableToFollowException("/// The buyer is already following the seller.");
            }
        }else{
            throw new UsersDoNotExistException("/// The id entered are not registered.");
        }
    }

    // 2 - Number of users who follow a certain seller
    @Override
    public SellerResponseSingleDTO countFollowersSeller(Integer userId) {
        var result = userRepository.findSellerById(userId);
        SellerResponseSingleDTO sellerResponseDTO = new SellerResponseSingleDTO();
        Seller mirror;
        if (result){
            mirror = userRepository.getSeller(userId);
            var count = userRepository.countFollowers(userId);
            sellerResponseDTO.setUserid(mirror.getUserId());
            sellerResponseDTO.setUserName(mirror.getUserName());
            sellerResponseDTO.setFollowers_count(count);
            return sellerResponseDTO;
        }else{
            throw new UsersDoNotExistException("/// The id entered does not correspond to a seller.");
        }
    }

    // 3 - List all user who follow a certain seller
    @Override
    public SellerResponseDTO listFollowersSeller(Integer userId) {
        var result = userRepository.findSellerById(userId);
        SellerResponseDTO sellerResponseDTO = new SellerResponseDTO();
        Seller mirror;
        if (result){
            mirror = userRepository.getSeller(userId);
            List<Buyer> buyers = userRepository.listFollowers(userId);
            sellerResponseDTO.setUserid(mirror.getUserId());
            sellerResponseDTO.setUserName(mirror.getUserName());
            sellerResponseDTO.setFollowers(buyers);
            return sellerResponseDTO;
        }else{
            throw new UsersDoNotExistException("/// The id entered does not correspond to a seller.");
        }
    }

    // 4 - List all seller followed by a certain user
    @Override
    public BuyerResponseDTO listFollowedSeller(Integer userId) {
        var result = userRepository.findBuyerById(userId);
        if (result){
            BuyerResponseDTO responseDTO = new BuyerResponseDTO();
            Buyer mirror = userRepository.getBuyer(userId);
            responseDTO.setUserId(mirror.getUserId());
            responseDTO.setUserName(mirror.getUserName());
            List<Integer> sellersID = userRepository.getSellerIdList(userId);
            List<Seller> sellerList = new ArrayList<>();
            for( Integer id : sellersID){
                Seller aux = userRepository.getSeller(id);
                sellerList.add(aux);
            }
            responseDTO.setFollowed(sellerList);
            return responseDTO;
        }else{
            throw new UsersDoNotExistException("/// The id entered does not correspond to a buyer.");
        }
    }

    // 7 - Buyer can unfollow a seller
    @Override
    public BuyerResponseMsgDTO unfollow(Integer userId, Integer userIdToUnfollow) {
        var buyerResult = userRepository.findBuyerById(userId);
        var sellerResult = userRepository.findSellerById(userIdToUnfollow);

        if (buyerResult & sellerResult){
            var aux =  userRepository.unfollowById(userId, userIdToUnfollow);
            if (aux){
                BuyerResponseMsgDTO buyerResponseMsgDTO = new BuyerResponseMsgDTO();
                buyerResponseMsgDTO.setMessage("The buyer "+userId+" has stopped following the seller "+userIdToUnfollow);
                return buyerResponseMsgDTO;
            }else {
                throw new UnrelatedUsersException("/// Users exist but there is no relationship between them.");
            }
        }else{
            throw new UsersDoNotExistException("/// The id entered are not registered.");
        }
    }

    // 8 - Ascending and descending alphabetical ordering
    @Override
    public SellerResponseDTO listFollowersSellerAscDes(Integer userId, String mode) {
        var exist = userRepository.findSellerById(userId);
        SellerResponseDTO sellerResponseDTO = new SellerResponseDTO();
        Seller mirror;
        if (mode == null) { mode = ""; }
        if (exist){
            mirror = userRepository.getSeller(userId);
            List<Buyer> buyers = userRepository.getBuyerlistFollowers(userId);
            if (mode.equals("name_asc")){
                Collections.sort(buyers, (o1, o2) -> o1.getUserName().compareToIgnoreCase(o2.getUserName()) );
            } else if (mode.equals("name_desc")){
                Collections.sort(buyers, (o1, o2) -> o2.getUserName().compareToIgnoreCase(o1.getUserName()) );
            }
            sellerResponseDTO.setUserid(mirror.getUserId());
            sellerResponseDTO.setUserName(mirror.getUserName());
            sellerResponseDTO.setFollowers(buyers);
            sellerResponseDTO.setFollowers_count(buyers.size());
            return sellerResponseDTO;
        }else{
            throw new UsersDoNotExistException("/// The id entered does not correspond to a seller.");
        }
    }

    @Override
    public BuyerResponseDTO listFollowedSellerAscDes(Integer userId, String mode) {
        var result = userRepository.findBuyerById(userId);
        if (result){
            BuyerResponseDTO responseDTO = new BuyerResponseDTO();
            Buyer mirror = userRepository.getBuyer(userId);
            if (mode == null) { mode = ""; }
            responseDTO.setUserId(mirror.getUserId());
            responseDTO.setUserName(mirror.getUserName());
            List<Integer> sellersID = userRepository.getSellerIdList(userId);
            List<Seller> sellerList = new ArrayList<>();
            for( Integer id : sellersID){
                Seller aux = userRepository.getSeller(id);
                sellerList.add(aux);
            }
            if (mode.equals("name_asc")){
                Collections.sort(sellerList, (o1, o2) -> o1.getUserName().compareToIgnoreCase(o2.getUserName()) );
            } else if (mode.equals("name_desc")){
                Collections.sort(sellerList, (o1, o2) -> o2.getUserName().compareToIgnoreCase(o1.getUserName()) );
            }
            responseDTO.setFollowed(sellerList);
            return responseDTO;
        }else{
            throw new UsersDoNotExistException("/// The id entered does not correspond to a buyer.");
        }
    }

    public String start() {
        return userRepository.load();
    }
}
