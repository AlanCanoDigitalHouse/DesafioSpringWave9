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

import java.text.ParseException;
import java.time.LocalDate;
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

    public ResponseEntity<HttpStatus> follow(Integer userId, Integer sellerId){
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
            return new ResponseEntity<>(HttpStatus.OK);
         }else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        for(Sellers seller: sellers){
            if(seller.getUserId().equals(sellerId)){
                respuesta.setUserName(seller.getUserName());
                respuesta.setUserId(sellerId);
                respuesta.setFollowers_count(countFollowers(sellerId));
            }
        }
        //dataOfSeller(sellerId,respuesta);
        return respuesta;
    }
//    private void dataOfSeller(int sellerId, FollowersCountDto respuesta){
//        for(Sellers seller: sellers){
//            if(seller.getUserId().equals(sellerId)){
//                respuesta.setUserName(seller.getUserName());
//                respuesta.setUserId(sellerId);
//                respuesta.setFollowers_count(countFollowers(sellerId));
//            }
//        }
//    }
    public FollowersListDto followersList(int sellerId,String order){
        FollowersListDto respuesta = new FollowersListDto();
        setFollowerList(sellerId,respuesta,order);
        return respuesta;
    }

    private void setFollowerList(int sellerId, FollowersListDto respuesta,String order){
        for(Sellers seller: sellers){
            if(seller.getUserId().equals(sellerId)){
                respuesta.setUserId(sellerId);
                respuesta.setUserName(seller.getUserName());
                if(order.equals("name_desc")){
                    List<Users> aux = followers.get(sellerId);
                    aux.sort((o1,o2) -> o2.getUserName().compareTo(o1.getUserName()));
                    respuesta.setFollowers(aux);
                }else{
                    List<Users> aux = followers.get(sellerId);
                    aux.sort((o1,o2) -> o1.getUserName().compareTo(o2.getUserName()));
                    respuesta.setFollowers(aux);
                }
            }
        }
    }

    public SellersFollowedListDto followedList(int userId,String order){
        SellersFollowedListDto respuesta = new SellersFollowedListDto();
        setFollowedList(userId,respuesta,order);
        return respuesta;
    }
    private void setFollowedList(int userId, SellersFollowedListDto respuesta,String order){
        for(Users user: users){
            if(user.getUserId().equals(userId)){
                respuesta.setUserId(userId);
                respuesta.setUserName(user.getUserName());
                respuesta.setFollowed(searchFollowedDetails(userId,order));
            }
        }
    }
    private List<Sellers> searchFollowedDetails(int userId,String order){
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
        if(order.equals("name_desc")){
            aux.sort((o1,o2) -> o2.getUserName().compareTo(o1.getUserName()));
        }else{
            aux.sort((o1,o2) -> o1.getUserName().compareTo(o2.getUserName()));
        }
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

    public UserSeller2WeeksListDto postList(int userId,String order) throws ParseException {
        UserSeller2WeeksListDto respuesta = new UserSeller2WeeksListDto();
        respuesta.setUserId(userId);
        respuesta.setPosts(orderList(userId,order));
        return respuesta;
    }

    private List<Integer> getSellersIdFollowed(int userId){
        List<Integer> respuesta = new ArrayList<>();
        List<Sellers> listaCompleta =followedList(userId,"date_desc").getFollowed();
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
    private List<NewPostDto> orderList(int userId,String order) throws ParseException {
        List<NewPostDto> listaDesordenada= getPostsWithUserId(userId);
        if(order.equals("date_asc")){
            listaDesordenada.sort((o1,o2) -> o1.getDate().compareTo(o2.getDate()));
        }else {
            listaDesordenada.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        }
        LocalDate fechaDosSemanas = LocalDate.now().minusDays(14);
        List<NewPostDto> listaOrdenada=new ArrayList<>();
        for(NewPostDto lista: listaDesordenada){
            if(convertDate(lista.getDate()).isAfter(fechaDosSemanas)){
                listaOrdenada.add(lista);
            }
        }
        return listaOrdenada;
    }
    private LocalDate convertDate(String date){
        String[] newDate= date.split("-");
        return LocalDate.of(Integer.parseInt(newDate[2]) ,Integer.parseInt(newDate[1]),Integer.parseInt(newDate[0]));
    }

    public HttpStatus unFollow(Integer userId, Integer sellerId){
        if(verifySeller(sellerId) && verifyUser(userId)){
            List<Users> usuarios = followers.get(sellerId);
            List<Users> usuariosFiltrados= new ArrayList<>();
            for(Users user: usuarios){
                if(!user.getUserId().equals(userId)){
                    usuariosFiltrados.add(user);
                }
            }
            followers.replace(sellerId,usuariosFiltrados);
            return HttpStatus.OK;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

}
