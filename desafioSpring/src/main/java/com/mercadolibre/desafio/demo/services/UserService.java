package com.mercadolibre.desafio.demo.services;

import com.mercadolibre.desafio.demo.dtos.response.FollowedListResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.FollowersCountResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.FollowersListResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.SuccessfullyResponseDTO;
import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.NotFoundUserException;
import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.RepeatUserIdException;
import com.mercadolibre.desafio.demo.models.BuyerModel;
import com.mercadolibre.desafio.demo.models.SellerModel;
import com.mercadolibre.desafio.demo.models.UserModel;

import com.mercadolibre.desafio.demo.repositories.DataBaseUserRepository;
import com.mercadolibre.desafio.demo.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    DataBaseUserRepository dataBaseUserRepository;
    UtilService utilService;

    public UserService(DataBaseUserRepository dataBaseUserRepository,UtilService utilService) {
        this.dataBaseUserRepository = dataBaseUserRepository;
        this.utilService = utilService;
    }

    // comunicación entre servicios ==========================
    public List<SellerModel> getFollowedList(UserModel userModel) {
        return dataBaseUserRepository.getFollowedList(userModel);
    }

    public List<UserModel> getList() {
        return this.dataBaseUserRepository.getList();
    }

    public void loadDataBase( List<UserModel> listUsers){
        this.dataBaseUserRepository.setList(listUsers);
    }

    public  void loadDataInList(){
        this.dataBaseUserRepository.loadSellers();
    }

    public Optional<UserModel> getFollower(Integer userId) {
        return this.dataBaseUserRepository.getFollower(userId);
    }

    public UserModel validateUserExist(Integer userId){
        Optional<UserModel> userDTO = this.dataBaseUserRepository.getFollower(userId);

        if (userDTO.isEmpty())
            throw new NotFoundUserException("Not found seller");

        return userDTO.get();
    }

    public void validateEqualsId( Integer sellerId, Integer buyerId ){
        if (sellerId.equals(buyerId))
            throw new RepeatUserIdException("identifiers cannot be the same",sellerId,buyerId);
    }

    // FIN comunicación entre servicios ==========================


    @Override
    public SuccessfullyResponseDTO addFollower(Integer sellerId, Integer buyerId)  {
        validateEqualsId(sellerId, buyerId);
        UserModel sellerDTO = validateUserExist( sellerId);
        UserModel buyerDTO = validateUserExist( buyerId);

        this.dataBaseUserRepository.addSeller(sellerDTO.getUserId());
        this.dataBaseUserRepository.addFollower(sellerId, new BuyerModel(buyerDTO.getUserId(),buyerDTO.getUserName()));
        return new SuccessfullyResponseDTO(HttpStatus.OK.value(),"follower added successfully");
    }

    @Override
    public FollowersCountResponseDTO countFollowers(Integer sellerId) {

        UserModel sellerDTO = validateUserExist( sellerId);

        this.dataBaseUserRepository.addSeller(sellerDTO.getUserId());
        return new FollowersCountResponseDTO(
                sellerDTO.getUserId(),sellerDTO.getUserName(),
                this.dataBaseUserRepository.countFollowers(sellerId)
        );
    }

    @Override
    public FollowersListResponseDTO listFollowers(Integer sellerId, String order) {

        UserModel sellerDTO = validateUserExist( sellerId);

        List<BuyerModel> list = this.dataBaseUserRepository.getFollowersList(sellerId);
        if (order!=null)
            this.utilService.orderBuyerByName(order,list);

        return new FollowersListResponseDTO(
                    sellerDTO.getUserId(),sellerDTO.getUserName(),
                    this.dataBaseUserRepository.getFollowersList(sellerId)
            );
    }

    @Override
    public FollowedListResponseDTO listFollowing(Integer buyerId, String order) {

        UserModel buyerDTO = validateUserExist( buyerId);

        List<SellerModel> list = this.dataBaseUserRepository.getFollowedList(buyerDTO);

        if (order!=null)
            this.utilService.orderSellerByName(order,list);

        return new FollowedListResponseDTO(
                buyerDTO.getUserId(),buyerDTO.getUserName(),
                list
        );
    }

    @Override
    public SuccessfullyResponseDTO unfollow(Integer sellerId, Integer buyerId) {
        validateEqualsId(sellerId, buyerId);
        UserModel sellerDTO = validateUserExist( sellerId);
        UserModel buyerDTO = validateUserExist( buyerId);
        this.dataBaseUserRepository.removeFollower(sellerDTO.getUserId(), new BuyerModel(buyerDTO.getUserId(),buyerDTO.getUserName()));
        return new SuccessfullyResponseDTO( HttpStatus.OK.value(),"follower removed successfully");
    }



}
