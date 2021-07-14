package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.models.Seller;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SellerRepository implements ISellerRepository {

    public static final String FILENAME = "sellers.json";

    @Override
    public Seller removeFollower(Long userId, Long sellerId) throws SellerException {
        List<Seller> sellers = this.loadDB(FILENAME, Seller.class);
        ;
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
        this.saveToFile(FILENAME, sellers);
        return seller;
    }

    @Override
    public List<Seller> findByFollowerUserId(Long userId) {
        List<Seller> sellers = this.loadDB(FILENAME, Seller.class);
        ;
        List<Seller> result = sellers.stream().filter(s -> s.getFollowers().stream().anyMatch(l -> l.equals(userId))).collect(Collectors.toList());
        return result;
    }

    @Override
    public Boolean checkIfSellerExistsById(Long sellerId) throws SellerException {
        List<Seller> sellers = this.loadDB(FILENAME, Seller.class);
        ;
        boolean result = sellers.stream().anyMatch(s -> s.getUserId().equals(sellerId));
        if (result)
            return true;
        else
            throw new SellerException(SellerException.SELLER_NOT_EXISTS + sellerId);
    }

    @Override
    public Seller findById(Long id) throws SellerException {
        List<Seller> sellers = this.loadDB(FILENAME, Seller.class);
        ;
        Optional<Seller> result = sellers.stream().filter(s -> s.getUserId().equals(id)).findAny();
        if (result.isPresent())
            return result.get();
        else
            throw new SellerException(SellerException.SELLER_NOT_EXISTS + id);
    }

    @Override
    public Collection<Seller> findByIds(Collection<Long> ids) {
        List<Seller> sellers = this.loadDB(FILENAME, Seller.class);
        ;
        return sellers.stream().filter(s -> ids.contains(s.getUserId())).collect(Collectors.toList());
    }

    @Override
    public Seller addFollower(Long userId, Long sellerId) throws SellerException {
        List<Seller> sellers = this.loadDB(FILENAME, Seller.class);
        ;
        Optional<Seller> optionalSeller = sellers.stream().filter(s -> s.getUserId().equals(sellerId)).findAny();
        Seller seller;
        if (optionalSeller.isPresent())
            seller = optionalSeller.get();
        else
            throw new SellerException(SellerException.SELLER_NOT_EXISTS + sellerId);

        if (seller.getFollowers().stream().anyMatch(f -> f.equals(userId)))
            throw new SellerException(SellerException.SELLER_ALREADY_FOLLOWED + sellerId);

        int sellerIndex = sellers.indexOf(seller);
        List<Long> followersNew = seller.getFollowers();
        followersNew.add(userId);
        seller.setFollowers(followersNew);
        sellers.set(sellerIndex, seller);
        this.saveToFile(FILENAME, sellers);
        return seller;
    }
}
