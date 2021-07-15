package com.example.desafiospring.services;

import com.example.desafiospring.dtos.*;
import com.example.desafiospring.dtos.responsedtos.SellerTotalFollowersResponseDTO;
import com.example.desafiospring.dtos.responsedtos.SellerWithAllFollowersResponseDTO;
import com.example.desafiospring.exceptions.OrderNotExistsException;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;
import com.example.desafiospring.repositories.ISellerRepository;
import com.example.desafiospring.utilities.FakeBD;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
@NoArgsConstructor
public class SellerServiceImpl implements ISellerService{

    @Autowired
    private ISellerRepository iSellerRepository;

    @Override
    public Boolean existsSeller(Integer idSeller) throws UserNotFoundByIdException {
        if(this.iSellerRepository.findSellerById(idSeller) != null){
            return true;
        }else throw new UserNotFoundByIdException();
    }

    @Override
    public SellerTotalFollowersResponseDTO getTotalFollowers(Integer idSeller) throws UserNotFoundByIdException{
        SellerDTO sellerDTO = this.iSellerRepository.findSellerById(idSeller);
        if (sellerDTO != null) {
            Integer sellerId = sellerDTO.getUserID();
            String sellerName = sellerDTO.getUsername();
            Integer sellerTotalFollowers;
            if(sellerDTO.getFollowers() !=null){
                 sellerTotalFollowers = sellerDTO.getFollowers().size();
            }else sellerTotalFollowers = 0;
            SellerTotalFollowersResponseDTO sellerTotalFollowersResponseDTO = new SellerTotalFollowersResponseDTO(sellerId, sellerName, sellerTotalFollowers);
            return sellerTotalFollowersResponseDTO;
        }else {
            throw new UserNotFoundByIdException();
        }
    }

    public SellerWithAllFollowersResponseDTO getFollowers(Integer idSeller, String order) throws UserNotFoundByIdException, OrderNotExistsException {
        SellerDTO sellerDTO = this.iSellerRepository.findSellerById(idSeller);
        if (sellerDTO != null) {
            String sellerName = sellerDTO.getUsername();
            List<BuyerFollowerDTO> followers = new ArrayList<>();
            SellerWithAllFollowersResponseDTO seller = new SellerWithAllFollowersResponseDTO(idSeller,sellerName);
            if(sellerDTO.getFollowers() !=null){
                for(BuyerFollowerDTO buyer: sellerDTO.getFollowers()){
                    followers.add(buyer);
                }
            }
            if(order != null){
                sortFollowerList(order, followers);
            }
            seller.setFollowers(followers);
            return seller;
        }else{
            throw new UserNotFoundByIdException();
        }
    }

    private void sortFollowerList(String order, List<BuyerFollowerDTO> followers) throws OrderNotExistsException {
        if(order.equals("data_asc")){
            followers.sort(
                    Comparator.comparing(UserDTO::getUsername)
            );
        }else if(order.equals("date_desc")){
            Comparator<BuyerFollowerDTO> comparator = Comparator.comparing(UserDTO::getUsername);
            followers.sort(comparator.reversed());
        }else{
            throw new OrderNotExistsException();
        }
    }

    @Override
    public SellerDTO findSellerDTOById(Integer idSeller) throws UserNotFoundByIdException{
        SellerDTO sellerById = this.iSellerRepository.findSellerById(idSeller);
        if(sellerById != null){
            return sellerById;
        }else throw new UserNotFoundByIdException();
    }

    @Override
    public void addFollower(SellerDTO sellerDTO, BuyerFollowerDTO follower){
        List<BuyerFollowerDTO> followers = sellerDTO.getFollowers();
        if(followers != null){
            followers.add(follower);
            sellerDTO.setFollowers(followers);
        }else{
            List<BuyerFollowerDTO> followersNew = new ArrayList<>();
            followersNew.add(follower);
            sellerDTO.setFollowers(followersNew);
        }
    }

    public void removeFollower(SellerDTO sellerDTO, Integer followerId){
        Iterator itr = sellerDTO.getFollowers().iterator();
        while (itr.hasNext()) {
            BuyerFollowerDTO follower = (BuyerFollowerDTO) itr.next();
            if (follower.getUserID().equals(followerId))
                itr.remove();
        }
    }
}
