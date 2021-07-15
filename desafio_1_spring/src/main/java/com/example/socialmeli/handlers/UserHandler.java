package com.example.socialmeli.handlers;

import com.example.socialmeli.dtos.requests.RequestPostDto;
import com.example.socialmeli.dtos.responses.*;
import com.example.socialmeli.exceptions.IncompatibleRequest;
import com.example.socialmeli.exceptions.InvalidOrder;
import com.example.socialmeli.models.Product;
import com.example.socialmeli.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserHandler {
    public static ResponseCantFollowersDto getCantFollowers(User user) {
        ResponseCantFollowersDto responseCantFollowersDto = new ResponseCantFollowersDto();
        responseCantFollowersDto.setUserId(user.getId());
        responseCantFollowersDto.setUserName(user.getName());
        responseCantFollowersDto.setFollowersCount(user.getFollowers().size());
        return responseCantFollowersDto;
    }

    private static void sortByName(List<ResponseUserDto> followersListsDto, String order) throws InvalidOrder {
        if(order.contentEquals("name_asc"))
            followersListsDto.sort(Comparator.comparing(ResponseUserDto::getUserName));
        else if(order.contentEquals("name_desc"))
            followersListsDto.sort(Comparator.comparing(ResponseUserDto::getUserName).reversed());
        else if(!order.contentEquals("none")){
            throw new InvalidOrder();
        }

    }

    private static List<ResponseUserDto> setUserListInfo(ArrayList<User> users, ResponseUserDto responseUserDto, User user, String order) throws InvalidOrder {
        responseUserDto.setUserId(user.getId());
        responseUserDto.setUserName(user.getName());

        List<ResponseUserDto> usersDto = users.stream().map(f-> new ResponseUserDto(f.getId(),f.getName())).collect(Collectors.toList());

        sortByName(usersDto,order);

        return usersDto;
    }

    public static ResponseFollowersDto getFollowersInfo(User user,String order) throws InvalidOrder {
        ResponseFollowersDto responseFollowersDto = new ResponseFollowersDto();
        ArrayList<User> followers = user.getFollowers();
        List<ResponseUserDto> followersListDto = setUserListInfo(followers,responseFollowersDto,user,order);

        responseFollowersDto.setFollowers(followersListDto);
        return responseFollowersDto;
    }

    public static ResponseFollowedDto getFollowedInfo(User user,String order) throws InvalidOrder {
        ResponseFollowedDto responseFollowedDto = new ResponseFollowedDto();
        ArrayList<User> followed = user.getFollowed();
        List<ResponseUserDto> followedListDto = setUserListInfo(followed,responseFollowedDto,user,order);

        responseFollowedDto.setFollowed(followedListDto);
        return responseFollowedDto;
    }

    public static void addUserPost(User user, RequestPostDto requestPostDto, Product product) throws IncompatibleRequest {
        ResponsePostDto responsePostDto= new ResponsePostDto();
        responsePostDto.setCategory(requestPostDto.getCategory());
        responsePostDto.setDate(requestPostDto.getDate());
        responsePostDto.setDetail(product);
        responsePostDto.setPrice(requestPostDto.getPrice());
        Double discount =  requestPostDto.getDiscount();
        if(Objects.nonNull(requestPostDto.getHasPromo()) && discount>0){
            if(requestPostDto.getHasPromo()){
                responsePostDto.setHasPromo(true);
                responsePostDto.setDiscount(discount);
            }else{
                throw new IncompatibleRequest();
            }
        }else{
            responsePostDto.setHasPromo(false);
            responsePostDto.setDiscount(0);
        }
        user.addPost(responsePostDto);
    }

    private static void sortByDate(ArrayList<ResponsePostDto> followersPosts, String order) throws InvalidOrder {
        if(order.contentEquals("date_asc"))
            followersPosts.sort(Comparator.comparing(ResponsePostDto::getDate));
        else if(order.contentEquals("date_desc"))
            followersPosts.sort(Comparator.comparing(ResponsePostDto::getDate).reversed());
        else if(!order.contentEquals("none")){
            throw new InvalidOrder();
        }

    }

    public static ResponsePostsListDto getUserPostsList(User user, String order) throws InvalidOrder {
        ResponsePostsListDto responsePostsListDto = new ResponsePostsListDto();
        responsePostsListDto.setUserId(user.getId());
        ArrayList<User> followed = user.getFollowed();

        ArrayList<ResponsePostDto> followersPosts = (ArrayList<ResponsePostDto>) followed.stream()
                .flatMap(user1 -> user1.getPosts().stream())
                .filter(p->p.getDate().isAfter(LocalDate.now().minusDays(14)))
                .collect(Collectors.toList());

        sortByDate(followersPosts,order);

        responsePostsListDto.setPosts(followersPosts);
        return responsePostsListDto;
    }
}
