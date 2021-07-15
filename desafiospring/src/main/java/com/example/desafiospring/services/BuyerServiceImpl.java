package com.example.desafiospring.services;

import com.example.desafiospring.dtos.*;
import com.example.desafiospring.dtos.responsedtos.BuyerWithAllFollowsResponseDTO;
import com.example.desafiospring.exceptions.OrderNotExistsException;
import com.example.desafiospring.exceptions.UserAlreadyFollowToSellerException;
import com.example.desafiospring.exceptions.UserNotFollowSellerException;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;
import com.example.desafiospring.repositories.IBuyerRepository;
import com.example.desafiospring.utilities.FakeBD;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@NoArgsConstructor
public class BuyerServiceImpl implements IBuyerService{

    @Autowired
    private IBuyerRepository iBuyerRepository;
    @Autowired
    private ISellerService iSellerService;

    @Override
    public void followSeller(Integer idBuyer, Integer idSeller) throws UserNotFoundByIdException, UserAlreadyFollowToSellerException {
        if(this.existsBuyer(idBuyer) && this.iSellerService.existsSeller(idSeller)){
            SellerDTO sellerDTO = this.iSellerService.findSellerDTOById(idSeller);
            BuyerDTO buyerDTO = this.iBuyerRepository.findUserById(idBuyer);

            if(this.checkAlreadyFollow(sellerDTO,buyerDTO)){
                throw new UserAlreadyFollowToSellerException();
            }else{
                SellerFollowedDTO sellerFollowedDTO = new SellerFollowedDTO(sellerDTO.getUserID(), sellerDTO.getUsername());
                BuyerFollowerDTO buyerFollowerDTO = new BuyerFollowerDTO(buyerDTO.getUserID(), buyerDTO.getUsername());

                this.addFollowedToBuyer(buyerDTO, sellerFollowedDTO);
                iSellerService.addFollower(sellerDTO,buyerFollowerDTO);
            }
        }else{
            throw new UserNotFoundByIdException();
        }
    }

    public void unFollowSeller(Integer idBuyer, Integer idSeller) throws UserNotFoundByIdException, UserNotFollowSellerException {
        if(this.existsBuyer(idBuyer) && this.iSellerService.existsSeller(idSeller)){

            SellerDTO sellerDTO = this.iSellerService.findSellerDTOById(idSeller);
            BuyerDTO buyerDTO = this.iBuyerRepository.findUserById(idBuyer);

            if(this.checkAlreadyFollow(sellerDTO,buyerDTO)){
                Iterator itr = buyerDTO.getFollowed().iterator();
                while (itr.hasNext()) {
                    SellerFollowedDTO follow = (SellerFollowedDTO) itr.next();
                    if (follow.getUserID().equals(idSeller))
                        itr.remove();
                        this.iSellerService.removeFollower(sellerDTO, buyerDTO.getUserID());
                        break;
                }
            }else{
                throw new UserNotFollowSellerException();
            }

        }else{
            throw new UserNotFoundByIdException();
        }
    }

    private void addFollowedToBuyer(BuyerDTO buyerDTO, SellerFollowedDTO sellerFollowedDTO){
        List<SellerFollowedDTO> followed = buyerDTO.getFollowed();
        if(followed != null){
            followed.add(sellerFollowedDTO);
        }else{
            List<SellerFollowedDTO> followedByUser = new ArrayList<>();
            followedByUser.add(sellerFollowedDTO);
            buyerDTO.setFollowed(followedByUser);
        }
    }

    private Boolean checkAlreadyFollow(SellerDTO sellerDTO, BuyerDTO buyerDTO){
        List<SellerFollowedDTO> followed = buyerDTO.getFollowed();
        if(followed != null){
            Optional<SellerFollowedDTO> item = buyerDTO.getFollowed().stream()
                    .filter(SellerFollowedDTO -> SellerFollowedDTO.getUserID().equals(sellerDTO.getUserID())).findFirst();
            return item.isPresent();
        }else{
            return false;
        }
    }

    @Override
    public Boolean existsBuyer(Integer idBuyer) {
        try {
            return this.iBuyerRepository.findUserById(idBuyer) != null;
        } catch (UserNotFoundByIdException e) {
            e.printStackTrace();
        }
        return false;
    }

    public BuyerWithAllFollowsResponseDTO getFollows(Integer buyerId, String order) throws UserNotFoundByIdException, OrderNotExistsException {
        BuyerDTO buyerDTO = this.iBuyerRepository.findUserById(buyerId);
        if(buyerDTO != null){
            String name = buyerDTO.getUsername();
            List<SellerFollowedDTO> follows = new ArrayList<>();
            BuyerWithAllFollowsResponseDTO buyer = new BuyerWithAllFollowsResponseDTO(buyerId, name);
            if(buyerDTO.getFollowed() !=null){
                for(SellerFollowedDTO seller: buyerDTO.getFollowed()){
                    follows.add(seller);
                }
            }
            if(order !=null){
                this.sortFollowsList(order,follows);
            }
            buyer.setFollows(follows);
            return buyer;
        }else{
            throw new UserNotFoundByIdException();
        }
    }

    private void sortFollowsList(String order, List<SellerFollowedDTO> follows) throws OrderNotExistsException {
        if(order.equals("name_asc")){
            follows.sort(
                    Comparator.comparing(UserDTO::getUsername)
            );
        }else if(order.equals("name_desc")){
            Comparator<SellerFollowedDTO> comparator = Comparator.comparing(UserDTO::getUsername);
            follows.sort(comparator.reversed());
        }else{
            throw new OrderNotExistsException();
        }
    }
}
