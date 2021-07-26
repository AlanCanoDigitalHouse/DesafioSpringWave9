package com.apismeli.socialmeli.services;

import com.apismeli.socialmeli.dtos.request.BuyerDTO;
import com.apismeli.socialmeli.dtos.request.MarketDTO;
import com.apismeli.socialmeli.dtos.request.UserDTO;
import com.apismeli.socialmeli.dtos.request.SellerDTO;
import com.apismeli.socialmeli.dtos.response.CountFollowersDTO;
import com.apismeli.socialmeli.exceptions.NotFoundExceptions;
import com.apismeli.socialmeli.repositories.InitializeDataRepositoryImpl;
import com.apismeli.socialmeli.utilities.compareUsernameAsc;
import com.apismeli.socialmeli.utilities.compareUsernameDesc;
import org.springframework.core.NestedIOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Collections;
import java.util.Objects;

@Service
public class UserService implements IUserService {
    public static MarketDTO marketDTO;
    private SellerDTO seller;
    private BuyerDTO buyer;

    InitializeDataRepositoryImpl inicializarDatosRepositoryImpl;

    public UserService(InitializeDataRepositoryImpl inicializarDatosRepositoryImpl) {
        this.inicializarDatosRepositoryImpl = inicializarDatosRepositoryImpl;
    }

    @Override
    public MarketDTO initializer() {
        inicializarDatosRepositoryImpl.initializeMarket();
        return marketDTO = inicializarDatosRepositoryImpl.getRedDTO();
    }


    @Override
    public ResponseEntity toFollow(Integer userId, Integer userIdtoFollow) throws Exception {

        try {
            BuyerDTO comprador = searchBuyer(userId);
            SellerDTO vendedor = searchSeller(userIdtoFollow);
            if (!vendedor.getFollowers().contains(new UserDTO(comprador.getUserId(), comprador.getUserName()))) {
                comprador.getFollowed().add(new UserDTO(userIdtoFollow, vendedor.getUserName()));
                vendedor.getFollowers().add(new UserDTO(userId, comprador.getUserName()));
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<String>( "You already follow this user",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseEntity unfollow(Integer userId, Integer userIdToUnfollow) throws Exception {
        try {
            BuyerDTO buyerDTO = searchBuyer(userId);
            SellerDTO sellerDTO = searchSeller(userIdToUnfollow);
            if (sellerDTO.getFollowers().contains(new UserDTO(buyerDTO.getUserId(), buyerDTO.getUserName()))) {
                buyerDTO.getFollowed().remove(new UserDTO(userIdToUnfollow, sellerDTO.getUserName()));
                sellerDTO.getFollowers().remove(new UserDTO(userId, buyerDTO.getUserName()));
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("You do not follow this user",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Object showUsers(Integer userID, String follow, String order) throws Exception {
        if (follow.equals("followers")) {
            if (Objects.isNull(order)) {
                return whoFollowsMe(userID);
            } else if (order.equals("name_desc") || order.equals("name_asc")) {
                return getOrderedFollowers(userID, order);
            } else {
                return new ResponseEntity<String>("Invalid order", HttpStatus.BAD_REQUEST);
            }
        }
        if (follow.equals("followed")) {
            if (Objects.isNull(order)) {
                return whoIFollow(userID);
            } else if (order.equals("name_desc") || order.equals("name_asc")) {
                return getOrderedFollowed(userID, order);
            } else {
                return new ResponseEntity<String>("Order invalido", HttpStatus.BAD_REQUEST);
            }
        }
        return null;
    }

    private SellerDTO whoFollowsMe(Integer userId) throws Exception {
        try {
            seller = searchSeller(userId);
            return seller;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public CountFollowersDTO countFollowers(Integer userId) throws Exception {
        try {
            seller = searchSeller(userId);
            return new CountFollowersDTO(userId, seller.getUserName(), seller.getFollowers().size());
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public BuyerDTO whoIFollow(Integer userId) throws Exception {
        try {
            buyer = searchBuyer(userId);
            return buyer;
        } catch (Exception e) {
            throw e;
        }
    }

    private SellerDTO searchSeller(Integer userIdtoFollow) throws Exception {

        for (SellerDTO seller : marketDTO.getSellers()) {
            if (seller.getUserId().equals(userIdtoFollow)) {
                return seller;
            }
        }
        throw new NotFoundExceptions();

    }

    private BuyerDTO searchBuyer(Integer userId) throws Exception {
        for (BuyerDTO buyer : marketDTO.getBuyers()) {
            if (buyer.getUserId().equals(userId)) {
                return buyer;
            }
        }
        throw new NotFoundExceptions();
    }


    private List<UserDTO> getOrderedFollowers(Integer userID, String order) throws Exception {
        try {
            if (order.equals("name_desc")) {
                seller = searchSeller(userID);
                Collections.sort(seller.getFollowers(), new compareUsernameDesc());
                return seller.getFollowers();
            } else {
                seller = searchSeller(userID);
                Collections.sort(seller.getFollowers(), new compareUsernameAsc());
                return seller.getFollowers();
            }


        } catch (Exception e) {
            throw e;
        }
    }

    private List<UserDTO> getOrderedFollowed(Integer userID, String order) throws Exception {
        try {
            if (order.equals("name_desc")) {
                buyer = searchBuyer(userID);
                Collections.sort(buyer.getFollowed(), new compareUsernameDesc());
                return buyer.getFollowed();
            } else {
                buyer = searchBuyer(userID);
                Collections.sort(buyer.getFollowed(), new compareUsernameAsc());
                return buyer.getFollowed();
            }


        } catch (Exception e) {
            throw e;
        }
    }


}
