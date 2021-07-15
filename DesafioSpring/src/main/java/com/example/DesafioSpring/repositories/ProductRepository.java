package com.example.DesafioSpring.repositories;

import com.example.DesafioSpring.models.Product;
import com.example.DesafioSpring.models.Publication;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class ProductRepository {

    private HashMap<Integer, Product> products = new HashMap<>();
    // id_product , Product
    private HashMap<Integer, Publication> publication = new HashMap<>();
    // id_post , Publication
    private HashMap<Integer, Integer> purchases = new HashMap<>();
    // userId(buyer) , id_post

    public String start(){
        Product p1 = new Product(100, "Silla gamer", "Gamer", "Racer", "Red & black", "Special edition");
        Product p2 = new Product(101, "Mouse gamer", "Gamer", "Redragon", "Black", "7 buttons");
        Product p3 = new Product(102, "Hub mac", "Office and home", "Satechi", "Silver", "8 ports");
        Product p4 = new Product(103, "Gaming mouse pad", "Gamer", "Huracan", "Black & RGB", "1,8 m");
        Product p5 = new Product(104, "Notebook", "Pro", "Apple", "Silver", "MeLi edition");
        Product p6 = new Product(105, "Display", "Gamer", "Samsung", "Black", "Ultra slim");
        Product p7 = new Product(106, "CPU", "Serie 5000", "Ryzen", "Silver", "5000 series processor");
        Product p8 = new Product(107, "Case gamer", "Gamer", "Redragon", "Black", "With armored glass");
        products.put(100, p1);
        products.put(101, p2);
        products.put(102, p3);
        products.put(103, p4);
        products.put(104, p5);
        products.put(105, p6);
        products.put(106, p7);
        products.put(107, p8);
        System.out.println("products = " + products);
        return "Loaded products.";
    }

    public Boolean findPublicationById(Integer id_post) {
        var result = publication.get(id_post);
        if (result == null) {
            return false;
        }else{
            return true;
        }
    }

    public void createNewPost(Publication post) {
        // registrar nuevo post
        var aux = this.findPublicationById(post.getId_post());
        if (aux) {
            // no se puede crear un post con un id ya usado
        }else{
            publication.putIfAbsent(post.getId_post(), post);
        }
    }

    public List<Publication> findPublicationBySellerID(Integer userId) {
        List<Publication> latestPost = new ArrayList<>();
        for (Map.Entry<Integer, Publication> item : publication.entrySet()){
            Integer sellerId = item.getValue().getUserId();
            if (sellerId == userId){
                Publication aux = new Publication();
                aux.setUserId(item.getValue().getUserId());
                aux.setId_post(item.getValue().getId_post());
                aux.setDate(item.getValue().getDate());
                aux.setProductID(item.getValue().getProductID());
                aux.setCategory(item.getValue().getCategory());
                aux.setPrice(item.getValue().getPrice());
                latestPost.add(aux);
            }
        }
        return latestPost;
    }

    public Boolean findSellerById(Integer userId) {
        for (Map.Entry<Integer, Publication> item : publication.entrySet()){
            Integer sellerId = item.getValue().getUserId();
            if (sellerId == userId){
                return true;
            }
        }
        return false;
    }

}
