package com.mercadolibre.desafio.demo.repositories;

import com.mercadolibre.desafio.demo.models.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DataBaseProductsRepository {

    // Injection of dependencies
    DataBaseUserRepository dataBaseUserRepository;
    // Variable definitions
    private Map<Integer,List<PublicModel>> productsRegistered = new HashMap<>();
    private Map<Integer,List<PublicPromoModel>> productsPromoRegistered = new HashMap<>();
    private List<ProductModel> products = new ArrayList<>();

    public DataBaseProductsRepository(DataBaseUserRepository dataBaseUserRepository) {
        this.dataBaseUserRepository = dataBaseUserRepository;
    }

    // load data
    public void loadSellers( List<UserModel> list){
        list.forEach(e -> {
            productsRegistered.put(e.getUserId(),new ArrayList<>());
            productsPromoRegistered.put(e.getUserId(),new ArrayList<>());
                }
        );
    }

    // create new post
    public void addPublic(Integer sellerId, PublicModel publicModel) {
        if (!this.productsRegistered.get(sellerId).contains(publicModel))
            this.productsRegistered.get(sellerId).add(publicModel);
    }

    // add promo public into value of map
    public void addPromoPublic(Integer sellerId, PublicPromoModel publicPromoModel) {
        if (!this.productsPromoRegistered.get(sellerId).contains(publicPromoModel))
            this.productsPromoRegistered.get(sellerId).add(publicPromoModel);
    }

    // get all posts from a seller
    public List<PublicModel> getPublicsBySellerList(List<SellerModel> sellers){
        List<PublicModel> publics = new ArrayList<>();
        sellers.forEach(seller -> {
            publics.addAll(this.productsRegistered.get(seller.getUserId()));
        });
        return publics;
    }

    // get public promo counter
    public Integer getPublicPromoCounter(Integer userId){
        return (int) this.productsPromoRegistered.get(userId).stream().filter(PublicPromoModel::getHasPromo).count();
    }

    // get publics promo list
    public List<PublicPromoModel> getPublicsPromo(Integer userID) {
        return this.productsPromoRegistered.get(userID);
    }

    // add new product
    public void addProduct(ProductModel productModel){
        if (!products.contains(productModel))
            products.add(productModel);
    }

    // get product
    public Optional<ProductModel> getProduct(Integer productId){
        return products.stream().filter(prod -> prod.getProduct_id().equals(productId)).findFirst();
    }

    // get all products
    public List<ProductModel> getProducts() {
        return products;
    }
}
