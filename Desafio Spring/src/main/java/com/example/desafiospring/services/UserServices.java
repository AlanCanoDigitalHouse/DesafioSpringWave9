package com.example.desafiospring.services;

import com.example.desafiospring.dtos.request.NewPostDto;
import com.example.desafiospring.dtos.response.FollowersCountDto;
import com.example.desafiospring.dtos.createData.Sellers;
import com.example.desafiospring.dtos.createData.Users;
import com.example.desafiospring.dtos.response.FollowersListDto;
import com.example.desafiospring.dtos.response.SellersFollowedListDto;
import com.example.desafiospring.dtos.response.UserSeller2WeeksListDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class UserServices {
    //creacion de usuarios
    public List<Users> users = new ArrayList<>();
    public List<Sellers> sellers= new ArrayList<>();
    private HashMap<Integer,List<Users>> followers = new HashMap<>();
    private List<NewPostDto> posts = new ArrayList<>();

    public List<Users> createUsers(String name){
        users.add(new Users(name));
        return users;
    }
    public List<Sellers> createSellers(String name) {
        sellers.add(new Sellers(name));
        return sellers;
    }
    public boolean verifyUser(Integer userId){
         boolean aux = false;
         for(Users user: users){
             if(user.getUserId().equals(userId)){
                 aux=true;
             }
         }
        return aux;
    }
    public boolean verifySeller(Integer sellerId){
        boolean aux=false;
        for(Sellers seller: sellers){
            if(seller.getUserId().equals(sellerId)){
                aux= true;
            }
        }
    return aux;
    }

    public HttpStatus follow(Integer userId, Integer sellerId){
        if(verifySeller(sellerId) && verifyUser(userId)){
            if(followers.containsKey(sellerId)){
                List<Users> aux = followers.get(sellerId);
                for(Users user: users){
                    if(user.getUserId().equals(userId)){
                        aux.add(user);
                    }
                }
                followers.replace(sellerId,aux);
            }else{
                List<Users> u = new ArrayList<>();
                for(Users user: users){
                    if(user.getUserId().equals(userId)){
                        u.add(user);
                        followers.put(sellerId,u);
                    }
                }
            }
            return HttpStatus.OK;
         }else{
           return HttpStatus.BAD_REQUEST;
        }
    }
    private int countFollowers(int sellerId){
        if(followers.containsKey(sellerId)){
            List<Users> count = followers.get(sellerId);
            return count.size();
        }else{
            return 0;
        }
    }
    public FollowersCountDto usersSellersCountDto(int sellerId){
        FollowersCountDto respuesta = new FollowersCountDto();
        dataOfSeller(sellerId,respuesta);
        return respuesta;
    }
    private void dataOfSeller(int sellerId, FollowersCountDto respuesta){
        for(Sellers seller: sellers){
            if(seller.getUserId().equals(sellerId)){
                respuesta.setUserName(seller.getUserName());
                respuesta.setUserId(sellerId);
                respuesta.setFollowers_count(countFollowers(sellerId));
            }
        }
    }
    public FollowersListDto followersList(int sellerId){
        FollowersListDto respuesta = new FollowersListDto();
        setFollowerList(sellerId,respuesta);
        return respuesta;
    }

    private void setFollowerList(int sellerId, FollowersListDto respuesta){
        for(Sellers seller: sellers){
            if(seller.getUserId().equals(sellerId)){
                respuesta.setUserId(sellerId);
                respuesta.setUserName(seller.getUserName());
                respuesta.setFollowers(followers.get(sellerId));
            }
        }
    }

    public SellersFollowedListDto followedList(int userId){
        SellersFollowedListDto respuesta = new SellersFollowedListDto();
        setFollowedList(userId,respuesta);
        return respuesta;
    }
    private void setFollowedList(int userId, SellersFollowedListDto respuesta){
        for(Users user: users){
            if(user.getUserId().equals(userId)){
                respuesta.setUserId(userId);
                respuesta.setUserName(user.getUserName());
                respuesta.setFollowed(searchFollowedDetails(userId));
            }
        }
    }
    private List<Sellers> searchFollowedDetails(int userId){
        List<Sellers> aux = new ArrayList<>();
        followers.forEach((k,v)-> {
            for(Users user:v){
                if(user.getUserId().equals(userId)){
                    for(Sellers seller : sellers){
                        if(seller.getUserId().equals(k)){
                            aux.add(seller);
                        }
                    }
                }
            }
        });
         return aux;
    }
    public HttpStatus createPost(NewPostDto post){
           if(Objects.nonNull(post)){
               posts.add(post);
               return HttpStatus.OK;
           }else{
               return HttpStatus.BAD_REQUEST;
           }
    }

    public UserSeller2WeeksListDto postList(int userId){
        UserSeller2WeeksListDto respuesta = new UserSeller2WeeksListDto();
        respuesta.setUserId(userId);
        respuesta.setPosts(getPostsWithUserId(userId));
        return respuesta;
    }

    private List<Integer> getSellersIdFollowed(int userId){
        List<Integer> respuesta = new ArrayList<>();
        List<Sellers> listaCompleta =followedList(userId).getFollowed();
        for(Sellers lista : listaCompleta){
                respuesta.add(lista.getUserId());
        }
        return respuesta;
    }
    private List<NewPostDto>  getPostsWithUserId(int userId){
        List<NewPostDto> finalList = new ArrayList<>();
        List<Integer> listSellerId = getSellersIdFollowed(userId);
        listSellerId.forEach(v->{
            for (NewPostDto post: posts){
                if(v.equals(post.getUserId())){
                    finalList.add(post);
                    }
                }
            });
            return finalList;
        }
    private List<NewPostDto> orderList(int userId){
        List<NewPostDto> listaDesordenada= getPostsWithUserId(userId);
        
    }


}
