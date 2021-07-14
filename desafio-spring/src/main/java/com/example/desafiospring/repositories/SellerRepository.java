package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.models.Seller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SellerRepository implements ISellerRepository {

    @Override
    public Seller removeFollower(Long userId, Long sellerId) throws SellerException {
        List<Seller> sellers = this.loadDB();
        Optional<Seller> optionalSeller = sellers.stream().filter(s -> s.getUserId().equals(sellerId)).findAny();
        Seller seller;
        if (optionalSeller.isPresent())
            seller = optionalSeller.get();
        else
            throw new SellerException(SellerException.SELLER_NOT_EXISTS);

        if (!seller.getFollowers().stream().anyMatch(f -> f.equals(userId)))
            throw new SellerException(SellerException.FOLLOWER_NOT_FOUND + sellerId);

        int sellerIndex = sellers.indexOf(seller);
        List<Long> followersNew = seller.getFollowers().stream().filter(f -> !f.equals(userId)).collect(Collectors.toList());
        seller.setFollowers(followersNew);
        sellers.set(sellerIndex, seller);
        this.saveToFile(sellers);
        return seller;
    }

    @Override
    public List<Seller> findByFollowerUserId(Long userId) {
        List<Seller> sellers = this.loadDB();
        List<Seller> result = sellers.stream().filter(s -> s.getFollowers().stream().anyMatch(l -> l.equals(userId))).collect(Collectors.toList());
        return result;
    }

    @Override
    public Seller findById(Long id) throws SellerException {
        List<Seller> sellers = this.loadDB();
        Optional<Seller> result = sellers.stream().filter(s -> s.getUserId().equals(id)).findAny();
        if (result.isPresent())
            return result.get();
        else
            throw new SellerException(SellerException.SELLER_NOT_EXISTS + id);
    }

    @Override
    public Collection<Seller> findByIds(Collection<Long> ids) {
        List<Seller> sellers = this.loadDB();
        return sellers.stream().filter(s -> ids.contains(s.getUserId())).collect(Collectors.toList());
    }

    private List<Seller> loadDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/sellers.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<Seller> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Seller>> typeReference = new TypeReference<>() {
        };
        List<Seller> sellers = null;
        try {
            sellers = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sellers;
    }

    private List<Seller> saveToFile(List<Seller> sellers) {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/sellers.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String listJson = objectMapper.writeValueAsString(sellers);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(listJson);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sellers;
    }

    @Override
    public Seller addFollower(Long userId, Long sellerId) throws SellerException {
        List<Seller> sellers = this.loadDB();
        Optional<Seller> optionalSeller = sellers.stream().filter(s -> s.getUserId().equals(sellerId)).findAny();
        Seller seller;
        if (optionalSeller.isPresent())
            seller = optionalSeller.get();
        else
            throw new SellerException(SellerException.SELLER_NOT_EXISTS);

        if (seller.getFollowers().stream().anyMatch(f -> f.equals(userId)))
            throw new SellerException(SellerException.SELLER_ALREADY_FOLLOWED + sellerId);

        int sellerIndex = sellers.indexOf(seller);
        List<Long> followersNew = seller.getFollowers();
        followersNew.add(userId);
        seller.setFollowers(followersNew);
        sellers.set(sellerIndex, seller);
        this.saveToFile(sellers);
        return seller;
    }
}
