package com.mercadolibre.desafiospring.repository;

import com.mercadolibre.desafiospring.exception.user.*;
import com.mercadolibre.desafiospring.model.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class UsersRepository {
    private Map<Integer, Client> clients;
    private Map<Integer, Seller> sellers;

    public UsersRepository() {
        this.clients = new HashMap<>();
        this.sellers = new HashMap<>();
    }

    public void checkClientExists(int userId)
            throws UserDoesNotExistsException {
        if (this.clients.getOrDefault(userId, null) == null) {
            throw new UserDoesNotExistsException();
        }
    }

    public void checkSellerExists(int userId)
            throws SellerDoesNotExistsException {
        if (this.sellers.getOrDefault(userId, null) == null) {
            throw new SellerDoesNotExistsException();
        }
    }

    public Client getClient(int userId) throws UserDoesNotExistsException {
        this.checkClientExists(userId);
        return this.clients.get(userId);
    }

    public Seller getSeller(int userId) throws SellerDoesNotExistsException {
        this.checkSellerExists(userId);
        return this.sellers.get(userId);
    }

    public void checkDuplicateUser(int id)
            throws DuplicateClientException, DuplicateSellerException {
        if (this.clients.getOrDefault(id, null) != null) {
            throw new DuplicateClientException();
        }

        if (this.sellers.getOrDefault(id, null) != null) {
            throw new DuplicateSellerException();
        }
    }

    public void addClient(int id, String name)
            throws DuplicateClientException, DuplicateSellerException {
        this.checkDuplicateUser(id);
        this.clients.put(id, new Client(id, name));
    }

    public void addSeller(int id, String name)
            throws DuplicateSellerException, DuplicateClientException {
        this.checkDuplicateUser(id);
        this.sellers.put(id, new Seller(id, name));
    }

    public void makeUserFollowSeller(int userId, int userIdToFollow)
            throws Exception {
        this.getClient(userId).follow(this.getSeller(userIdToFollow));
        this.sellers.get(userIdToFollow).addFollower(this.clients.get(userId));
    }

    public int numberOfFollowersOf(int userId)
            throws SellerDoesNotExistsException {
        return this.getSeller(userId).getNumberOfFollowers();
    }

    public List<Post> getFollowedLastsPosts(int userId, String order)
            throws UserDoesNotExistsException {
        List<Post> posts = new ArrayList<>();

        this.getClient(userId).getFollowed().forEach((seller) -> {
            posts.addAll(seller.getLastsPosts());
        });

        posts.sort(Post.sortings.get(order));
        return posts;
    }

    public void makeUserUnfollowSeller(int userId, int userIdToUnfollow)
            throws Exception {
        this.getClient(userId).unfollow(this.getSeller(userIdToUnfollow));
        this.sellers.get(userIdToUnfollow).removeFollower(
                this.clients.get(userId));
    }

    public void addPostToSeller(int userId, Post post)
            throws SellerDoesNotExistsException {
        this.getSeller(userId).addPost(post);
    }

    public void addPromoPostToSeller(int userId, Post post)
            throws SellerDoesNotExistsException {
        this.getSeller(userId).addPromoPost(post);
    }

    public int getPromoPostNumberOf(int userId)
            throws SellerDoesNotExistsException {
        return this.getSeller(userId).getPromoPosts().size();
    }
}
