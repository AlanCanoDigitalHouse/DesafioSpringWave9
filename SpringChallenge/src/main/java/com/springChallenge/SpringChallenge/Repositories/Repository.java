package com.springChallenge.SpringChallenge.Repositories;

import com.springChallenge.SpringChallenge.Dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class Repository implements IRepository{
    private static List<Seller> SELLERS = chargeSellers();


    private static List<Shopper> SHOPPERS= chargeShopper();
    private static List<Post> POST= chargePosts();
    private static int POSTCOUNT = 6;

    @Override
    public void follow(int userId, int userIdToFollow) {
        var shopper = SHOPPERS.stream().filter(s -> s.getUserId() == userId).findFirst().orElse(null);
        var seller = SELLERS.stream().filter(s -> s.getUserId() == userIdToFollow).findFirst().orElse(null);

        if( shopper == null || seller == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Seller or Shopper not exist"
            );
        }
        SELLERS.forEach(s -> {
            if (s.getUserId() == userIdToFollow)
                s.getFollowers().add(shopper);
        });

    }

    @Override
    public Seller getSellerById(int userId) {
        return SELLERS.stream().filter(s -> s.getUserId() == userId).findFirst().orElse(null);
    }

    @Override
    public Shopper getSellersByShopperId(int userId, String order) {
        Shopper shopper = SHOPPERS.stream().filter(s -> s.getUserId() == userId).findFirst().orElse(null);
        List<User> followed = new ArrayList<>();
        for (Seller s: SELLERS) {
            if(s.getFollowers().contains(shopper)){
                followed.add(new User(s.getUserId(), s.getUserName()));
            }
        }
        shopper.setFollowed(followed);
        if(order != null && order.equals("name_asc")){
            shopper.getFollowed().sort(Comparator.comparing(User::getUserName));
        }
        if(order != null && order.equals("name_desc")){
            shopper.getFollowed().sort(Comparator.comparing(User::getUserName).reversed());
        }
        return shopper;
    }

    @Override
    public void addProduct(Post post){
        post.setId_post(POSTCOUNT);
        post.getDetail().setProduct_id(POSTCOUNT);

        POSTCOUNT = POSTCOUNT +1;
        POST.add(post);
    }

    @Override
    public boolean existUser(int userId) {
        return SELLERS.stream().anyMatch(s -> s.getUserId() == userId);
    }

    @Override
    public List<Post> followedPost(int userId, String sort){
        List<Post> result = new ArrayList<>();
        List<Integer> sellers = SELLERS.stream().filter(s -> s.getFollowers().stream()
                                      .anyMatch(shop -> shop.getUserId() == userId))
                                      .map(User::getUserId)
                                      .collect(Collectors.toList());
        if(sellers.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Sellers not found"
            );
        }
        for (Post p: POST) {
            for (int i: sellers) {
                if(p.getUserId() == i){
                    result.add(p);
                }
            }
        }
        if(sort != null && sort.equals("date_asc")){
            result.sort(Comparator.comparing(Post::getDate));
        }else{
            result.sort(Comparator.comparing(Post::getDate).reversed());
        }

        return result;

    }

    public void unfollow(int userId, int userIdToUnfollow){
        var shopper = SHOPPERS.stream().filter(s -> s.getUserId() == userId).findFirst().orElse(null);
        var seller = SELLERS.stream().filter(s -> s.getUserId() == userIdToUnfollow).findFirst().orElse(null);

        if( shopper == null || seller == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Seller or Shopper not exist"
            );
        }
        SELLERS.forEach(s -> {
            if (s.getUserId() == userIdToUnfollow)
                s.getFollowers().remove(shopper);
        });
    }



    //Metodos de carga de datos iniciales para pruebas
    private static List<Seller> chargeSellers(){
     List<Seller> sellers = new ArrayList<>();
     sellers.add(new Seller(1,"Alfredo Rodriguez"));
     sellers.add(new Seller(2,"Marta Gimenez"));
     sellers.add(new Seller(3,"Laura Paz"));
     sellers.add(new Seller(4,"Lorenzo Martinez"));
     return sellers;
    }

    private static List<Shopper> chargeShopper(){
        List<Shopper> sellers = new ArrayList<>();
        sellers.add(new Shopper(5,"Veronica Rodriguez"));
        sellers.add(new Shopper(6,"Martin Costa"));
        sellers.add(new Shopper(7,"Luciana Vigna"));
        sellers.add(new Shopper(8,"Pedro Dimarco"));
        return sellers;
    }

    private static List<Post> chargePosts(){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1,
                    1,
                           new GregorianCalendar(2019, Calendar.DECEMBER, 29).getTime(),
                           new Product(1, "Mesa de Luz", "Dormitorio", "Artico", "negro", "estilo nordico"),
                  100,
                     2000.0));
        posts.add(new Post(1,
                2,
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 30).getTime(),
                new Product(2, "Lampara", "Pie", "Light", "Cobre", "estilo nordico"),
                200,
                1250.0));
        posts.add(new Post(2,
                3,
                new GregorianCalendar(2020, Calendar.MAY, 15).getTime(),
                new Product(3, "Camara de fotos", "Mirrorless", "Cannon", "negro", "26mp"),
                300,
                20000.0));
        posts.add(new Post(3,
                4,
                new GregorianCalendar(2019, Calendar.OCTOBER, 18).getTime(),
                new Product(4, "Mesa de Luz", "Dormitorio", "Rusticos and Co", "Nogal", "Minimalista"),
                100,
                3500.0));
        posts.add(new Post(3,
                5,
                new GregorianCalendar(2021, Calendar.FEBRUARY, 5).getTime(),
                new Product(5, "TV", "Electrodomestico", "Samsung", "Negro", "32 pulgadas y 4k"),
                100,
                3500.0));
        return posts;
    }




}
