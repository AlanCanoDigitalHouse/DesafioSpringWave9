package com.example.desafiospring.services;

import com.example.desafiospring.dto.response.SellerResponseDTO;
import com.example.desafiospring.dto.response.UserResponseDTO;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.exceptions.UserException;
import com.example.desafiospring.models.Seller;
import com.example.desafiospring.models.User;
import com.example.desafiospring.repositories.ISellerRepository;
import com.example.desafiospring.repositories.IUserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    IUserRepository userRepository;
    ISellerRepository sellerRepository;
    ObjectMapper mapper;
    private static final Map<String, Comparator<User>> ORDER_PARAMS = Map.of(
            "name_asc", Comparator.comparing(User::getUserName),
            "name_desc", Comparator.comparing(User::getUserName).reversed(),
            "id", Comparator.comparing(User::getUserId)
    );

    public UserService(IUserRepository userRepository, ISellerRepository sellerRepository, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.mapper = mapper;
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void followSeller(Long userId, Long sellerId) throws SellerException {
        this.sellerRepository.addFollower(userId, sellerId);
    }

    @Override
    public SellerResponseDTO getNumberOfFollowers(Long sellerId) throws SellerException {
        Seller seller = this.sellerRepository.findById(sellerId);
        Long numberOfFollowers = seller.getFollowers().stream().count();
        SellerResponseDTO responseDTO = new SellerResponseDTO(
                seller.getUserId(), seller.getUserName(), numberOfFollowers
        );
        return responseDTO;
    }

    @Override
    public SellerResponseDTO getFollowers(Long sellerId, String order) throws SellerException {
        Seller seller = this.sellerRepository.findById(sellerId);
        Collection<User> followers = this.userRepository.findByIds(seller.getFollowers());
        Collection<User> followersOrdered = followers.stream().sorted(ORDER_PARAMS.getOrDefault(order, Comparator.comparing(User::getUserId))).collect(Collectors.toList());
        List<UserResponseDTO> dtoFollowers = followersOrdered.stream().map(f -> new UserResponseDTO(f.getUserId(), f.getUserName())).collect(Collectors.toList());
        SellerResponseDTO responseDTO = new SellerResponseDTO(
                seller.getUserId(), seller.getUserName(), dtoFollowers
        );
        return responseDTO;
    }

    @Override
    public UserResponseDTO getFollowed(Long userId, String order) throws UserException {
        User user = this.userRepository.findById(userId);
        List<Seller> sellers = this.sellerRepository.findByFollowerUserId(userId);
        List<Seller> sellersOrdered = sellers.stream().sorted(ORDER_PARAMS.getOrDefault(order, Comparator.comparing(User::getUserId))).collect(Collectors.toList());
        List<SellerResponseDTO> sellerResponseDTOs =
                sellersOrdered.stream().map(s -> mapper.convertValue(s, SellerResponseDTO.class)).collect(Collectors.toList());
        UserResponseDTO responseDTO = new UserResponseDTO(user.getUserId(), user.getUserName(), sellerResponseDTOs);
        return responseDTO;
    }

    @Override
    public void unfollowSeller(Long userId, Long sellerId) throws SellerException {
        this.sellerRepository.removeFollower(userId, sellerId);
    }
}
